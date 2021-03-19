package com.ihfazh.moviecatalog.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ihfazh.moviecatalog.data.datasources.TMDBDataSource;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.remote.RemoteDataSource;
import com.ihfazh.moviecatalog.data.responses.MovieDetail;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
import com.ihfazh.moviecatalog.data.responses.TVDetail;
import com.ihfazh.moviecatalog.data.responses.TVResultItem;
import com.ihfazh.moviecatalog.utils.EspressoIdlingResources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TMDBRepository implements TMDBDataSource {
    private static final String TAG = "TMDBRepository";
    private final RemoteDataSource dataSource;

    @Inject
    public TMDBRepository(RemoteDataSource dataSource) {
        this.dataSource = dataSource;
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

    @Override
    public LiveData<PagedList<MovieEntity>> getMovies() {

        DataSource.Factory<Integer, MovieEntity> liveData = dataSource.listMovie().map(TMDBRepository::apply);

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setInitialLoadSizeHint(20).build();
        
        return new LivePagedListBuilder<>(liveData, config).build();
    }

    @Override
    public LiveData<List<TvShowEntity>> getTvShows() {
        MutableLiveData<List<TvShowEntity>> tvShows = new MutableLiveData<>();
        EspressoIdlingResources.increment();
        dataSource.listTvShows(new RemoteDataSource.DataSourceCallback<List<TVResultItem>>() {
            @Override
            public void onSuccess(List<TVResultItem> response) {
                ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();
                for (TVResultItem item: response){
                    TvShowEntity tvShow = new TvShowEntity();
                    tvShow.setTitle(item.getName());
                    tvShow.setOverview(item.getOverview());
                    tvShow.setId(String.valueOf(item.getId()));
                    tvShow.setPoster_url(item.getPosterPath());
                    tvShowEntities.add(tvShow);
                }
                tvShows.postValue(tvShowEntities);
                EspressoIdlingResources.decrement();

            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, "onError: " + errorMessage );

            }
        });
        return tvShows ;
    }

    public LiveData<MovieEntity> getMovieById(String id) {
        MutableLiveData<MovieEntity> movieEntity = new MutableLiveData<>();
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
                movieEntity.setValue(entity);
                EspressoIdlingResources.decrement();
            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, "onError: " + errorMessage);
            }
        });
        return movieEntity;
    }

    public LiveData<TvShowEntity> getTvById(String id){
        MutableLiveData<TvShowEntity> tvShowEntity = new MutableLiveData<>();
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
                tvShowEntity.postValue(entity);
                EspressoIdlingResources.decrement();

            }

            @Override
            public void onError(String errorMessage) {
                Log.e(TAG, "onError: " + errorMessage);

            }
        });
        return tvShowEntity;
    }
}
