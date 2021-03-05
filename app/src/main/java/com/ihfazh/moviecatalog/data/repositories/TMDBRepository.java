package com.ihfazh.moviecatalog.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ihfazh.moviecatalog.data.datasources.TMDBDataSource;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
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
    private ApiService apiService;

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
}
