package com.ihfazh.moviecatalog.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ihfazh.moviecatalog.data.datasources.TMDBDataSource;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.responses.MovieDetail;
import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
import com.ihfazh.moviecatalog.data.responses.TVDetail;
import com.ihfazh.moviecatalog.data.responses.TVListResponse;
import com.ihfazh.moviecatalog.data.responses.TVResultItem;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TMDBRepository implements TMDBDataSource {
    private static final String TAG = "TMDBRepository";
    private final ApiService apiService;

    @Inject
    public TMDBRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public LiveData<List<MovieEntity>> getMovies() {
        MutableLiveData<List<MovieEntity>> liveData = new MutableLiveData<>();

        apiService.listMovie().enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                ArrayList<MovieEntity> movieList = new ArrayList<>();
                for (MovieResultItem item: response.body().getResults()){
                  MovieEntity movie = new MovieEntity();
                  movie.setTitle(item.getTitle());
                  movie.setPosterUrl(item.getPosterPath());
                  movie.setId(String.valueOf(item.getId()));
                  movieList.add(movie);
                }

                liveData.postValue(movieList);
                Log.d(TAG, "onResponse: success");
                Log.d(TAG, "onResponse: " + movieList.toString());
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "  + t.toString());

            }
        });

        return liveData;
    }

    @Override
    public LiveData<List<TvShowEntity>> getTvShows() {
        MutableLiveData<List<TvShowEntity>> tvShows = new MutableLiveData<>();
        apiService.listTv().enqueue(new Callback<TVListResponse>() {
            @Override
            public void onResponse(Call<TVListResponse> call, Response<TVListResponse> response) {
                ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();
                for (TVResultItem item: response.body().getResults()){
                    TvShowEntity tvShow = new TvShowEntity();
                    tvShow.setTitle(item.getName());
                    tvShow.setOverview(item.getOverview());
                    tvShow.setId(String.valueOf(item.getId()));
                    tvShow.setPoster_url(item.getPosterPath());
                    tvShowEntities.add(tvShow);
                }
                tvShows.postValue(tvShowEntities);
            }

            @Override
            public void onFailure(Call<TVListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return tvShows ;
    }

    public LiveData<MovieEntity> getMovieById(String id) {
        MutableLiveData<MovieEntity> movieEntity = new MutableLiveData<>();
        apiService.getMovie(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                MovieDetail detail = response.body();
                MovieEntity entity = new MovieEntity();
                entity.setTitle(detail.getTitle());
                entity.setPosterUrl(detail.getPosterPath());
                entity.setBudget(String.valueOf(detail.getBudget()));
                entity.setScore(String.valueOf(detail.getVoteAverage()));
                entity.setOverview(detail.getOverview());
                entity.setLanguage(detail.getOriginalLanguage());
                entity.setLength(String.valueOf(detail.getRuntime()));
                entity.setStatus(detail.getStatus());
                movieEntity.setValue(entity);
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return movieEntity;
    }

    public LiveData<TvShowEntity> getTvById(String id){
        MutableLiveData<TvShowEntity> tvShowEntity = new MutableLiveData<>();
        apiService.getTv(id).enqueue(new Callback<TVDetail>() {
            @Override
            public void onResponse(Call<TVDetail> call, Response<TVDetail> response) {
                TVDetail detail = response.body();
                TvShowEntity entity = new TvShowEntity();
                entity.setTitle(detail.getName());
                entity.setPoster_url(detail.getPosterPath());
                entity.setOverview(detail.getOverview());
                entity.setScore(String.valueOf(detail.getVoteAverage()));
                entity.setType(detail.getType());
                entity.setId(String.valueOf(detail.getId()));
                entity.setStatus(detail.getStatus());
                tvShowEntity.postValue(entity);
            }

            @Override
            public void onFailure(Call<TVDetail> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString() );

            }
        });
        return tvShowEntity;
    }
}
