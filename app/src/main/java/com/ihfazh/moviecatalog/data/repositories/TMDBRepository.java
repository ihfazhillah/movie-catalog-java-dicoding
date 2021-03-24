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
import com.ihfazh.moviecatalog.data.responses.TVDetail;
import com.ihfazh.moviecatalog.utils.AppExecutors;
import com.ihfazh.moviecatalog.utils.EspressoIdlingResources;

import javax.inject.Inject;

public class TMDBRepository implements TMDBDataSource {
    private static final String TAG = "TMDBRepository";
    private final RemoteDataSource dataSource;
    private final AppDatabase localSource;
    private final AppExecutors executors;

    @Inject
    public TMDBRepository(RemoteDataSource dataSource, AppDatabase database, AppExecutors executors) {
        this.dataSource = dataSource;
        this.localSource = database;
        this.executors = executors;
    }


    private PagedList.Config getPagingConfig(){
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setInitialLoadSizeHint(20).build();
        return config;
    }

    @Override
    public LiveData<PagedList<MovieEntity>> getMovies() {

        EspressoIdlingResources.increment();
        DataSource.Factory<Integer, MovieEntity> liveData = dataSource.listMovie();
        EspressoIdlingResources.decrement();

        return new LivePagedListBuilder<>(liveData, getPagingConfig()).build();
    }

    @Override
    public LiveData<PagedList<TvShowEntity>> getTvShows() {

        EspressoIdlingResources.increment();
        DataSource.Factory<Integer, TvShowEntity> factory = dataSource.listTvShows();
        EspressoIdlingResources.decrement();

        return new LivePagedListBuilder<>(factory, getPagingConfig()).build();
    }

    public LiveData<MovieEntity> getMovieById(String id) {
        MediatorLiveData<MovieEntity> result = new MediatorLiveData<>();
        LiveData<MovieEntity> dbSource = localSource.getMovie(id);
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

                                executors.diskIO().execute(() -> {
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
        LiveData<TvShowEntity> dbSource = localSource.getTv(id);
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

                                executors.diskIO().execute(() -> {
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
        executors.diskIO().execute(
                () -> localSource.movieDao().update(entity)
        );
    }

    public void updateTv(TvShowEntity entity) {
        executors.diskIO().execute(
                () -> {
                   localSource.tvDao().update(entity);
                }
        );

    }

    public LiveData<PagedList<TvShowEntity>> getBookmarkedTv(SimpleSQLiteQuery query) {
        DataSource.Factory<Integer, TvShowEntity> factory = localSource.getBookmarkedTv(query);
        return new LivePagedListBuilder<>(factory, getPagingConfig()).build();
    }

    public LiveData<PagedList<MovieEntity>> getBookmarkedMovie(SimpleSQLiteQuery query) {
        DataSource.Factory<Integer, MovieEntity> factory = localSource.getBookmarkedMovie(query);
        return new LivePagedListBuilder<>(factory, getPagingConfig()).build();
    }

    public void setBookmark(MovieEntity entity, boolean state) {
        entity.setBookmarked(state);
        this.updateMovie(entity);
    }
    public void setBookmark(TvShowEntity entity, boolean state) {
        entity.setBookmarked(state);
        this.updateTv(entity);
    }
}
