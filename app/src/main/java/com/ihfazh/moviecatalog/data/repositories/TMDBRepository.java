package com.ihfazh.moviecatalog.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.ihfazh.moviecatalog.data.datasources.TMDBDataSource;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.local.AppDatabase;
import com.ihfazh.moviecatalog.data.remote.RemoteDataSource;
import com.ihfazh.moviecatalog.data.responses.MovieDetail;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
import com.ihfazh.moviecatalog.data.responses.TVDetail;
import com.ihfazh.moviecatalog.data.responses.TVResultItem;
import com.ihfazh.moviecatalog.utils.EspressoIdlingResources;
import com.ihfazh.moviecatalog.utils.sql.MovieSqlHelper;
import com.ihfazh.moviecatalog.utils.sql.Sort;
import com.ihfazh.moviecatalog.utils.sql.TvSqlHelper;

import java.util.concurrent.Executors;

import javax.inject.Inject;

public class TMDBRepository implements TMDBDataSource {
    private static final String TAG = "TMDBRepository";
    private final RemoteDataSource dataSource;
    private final AppDatabase localSource;

    @Inject
    public TMDBRepository(RemoteDataSource dataSource, AppDatabase database) {
        this.dataSource = dataSource;
        this.localSource = database;
    }

    private static MovieEntity apply(MovieResultItem item) {
        return new MovieEntity(
                String.valueOf(item.getId()),
                item.getPosterPath(),
                item.getTitle(),
                null,
                null,
                null,
                null,
                item.getOverview(),
                null
        );
    }

    private static TvShowEntity apply(TVResultItem item){
        return new TvShowEntity(
                String.valueOf(item.getId()),
                item.getPosterPath(),
                item.getName(),
                null,
                null,
                null,
                null
        );
    }

    @Override
    public LiveData<PagedList<MovieEntity>> getMovies() {

        EspressoIdlingResources.increment();
        DataSource.Factory<Integer, MovieEntity> liveData = dataSource.listMovie().map(TMDBRepository::apply);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setInitialLoadSizeHint(20).build();
        EspressoIdlingResources.decrement();

        return new LivePagedListBuilder<>(liveData, config).build();
    }

    @Override
    public LiveData<PagedList<TvShowEntity>> getTvShows() {
        EspressoIdlingResources.increment();
        DataSource.Factory<Integer, TvShowEntity> factory = dataSource.listTvShows().map(TMDBRepository::apply);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .build();
        EspressoIdlingResources.decrement();
        return new LivePagedListBuilder<>(factory, config).build();
    }

    public LiveData<MovieEntity> getMovieById(String id) {
        MediatorLiveData<MovieEntity> result = new MediatorLiveData<>();
        LiveData<MovieEntity> dbSource = localSource.movieDao().getMovie(id);
        result.addSource(
                dbSource,
                newData -> {
                    if (newData != null){
                        result.setValue(newData);
                        Log.d(TAG, "getMovieById: get from db");
                    } else {
                        Log.d(TAG, "getMovieById: get from remote");
                        EspressoIdlingResources.increment();
                        dataSource.getMovieById(id, new RemoteDataSource.DataSourceCallback<MovieDetail>() {
                            @Override
                            public void onSuccess(MovieDetail response) {
                                MovieEntity entity = new MovieEntity();
                                entity.setTitle(response.getTitle());
                                entity.setPosterUrl(response.getPosterPath());
                                entity.setBudget(String.valueOf(response.getBudget()));
                                entity.setScore(String.valueOf(response.getVoteAverage()));
                                entity.setOverview(response.getOverview());
                                entity.setLanguage(response.getOriginalLanguage());
                                entity.setLength(String.valueOf(response.getRuntime()));
                                entity.setStatus(response.getStatus());
                                entity.setId(String.valueOf(response.getId()));
                                result.setValue(entity);

                                Executors.newSingleThreadExecutor().execute(() -> {
                                    localSource.movieDao().insert(entity);
                                });

                                EspressoIdlingResources.decrement();
                            }

                            @Override
                            public void onError(String errorMessage) {
                                Log.e(TAG, "onError: " + errorMessage);
                            }
                        });
                    }
                }
        );
        return result;
    }

    public LiveData<TvShowEntity> getTvById(String id){
        MediatorLiveData<TvShowEntity> result = new MediatorLiveData<>();
        LiveData<TvShowEntity> dbSource = localSource.tvDao().getTv(id);
        result.addSource(
                dbSource,
                newData -> {
                    if (newData != null){
                        result.setValue(newData);
                    } else {
                        EspressoIdlingResources.increment();
                        dataSource.getTvById(id, new RemoteDataSource.DataSourceCallback<TVDetail>() {
                            @Override
                            public void onSuccess(TVDetail response) {
                                TvShowEntity entity = new TvShowEntity();
                                entity.setTitle(response.getName());
                                entity.setPoster_url(response.getPosterPath());
                                entity.setOverview(response.getOverview());
                                entity.setScore(String.valueOf(response.getVoteAverage()));
                                entity.setType(response.getType());
                                entity.setId(String.valueOf(response.getId()));
                                entity.setStatus(response.getStatus());

                                result.setValue(entity);
                                Executors.newSingleThreadExecutor().execute(() -> {
                                    localSource.tvDao().insert(entity);
                                });

                                EspressoIdlingResources.decrement();

                            }

                            @Override
                            public void onError(String errorMessage) {
                                Log.e(TAG, "onError: " + errorMessage);

                            }
                        });
                    }
                }
        );
        return result;
    }

    public void updateMovie(MovieEntity entity) {
        Executors.newSingleThreadExecutor().execute(
                () -> {localSource.movieDao().update(entity);}
        );
    }

    public void updateTv(TvShowEntity entity) {
        Executors.newSingleThreadExecutor().execute(
                () -> {localSource.tvDao().update(entity);}
        );

    }

    public DataSource.Factory<Integer, TvShowEntity> getBookmarkedTv(Sort sort) {
        SimpleSQLiteQuery query = TvSqlHelper.getListBookmarked(sort);
        return localSource.tvDao().getBookmarkedTvShowsSort(query);
    }

    public DataSource.Factory<Integer, MovieEntity> getBookmarkedMovie(Sort sort) {
        SimpleSQLiteQuery query = MovieSqlHelper.getListBookmarked(sort);
        return localSource.movieDao().getBookmarkedMovieSort(query);
    }

    public void setBookmark(MovieEntity entity, boolean state) {
        entity.setBookmarked(state);
        this.updateMovie(entity);
    }
}
