package com.ihfazh.moviecatalog.data;

import com.ihfazh.moviecatalog.data.responses.MovieDetail;
import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
import com.ihfazh.moviecatalog.data.responses.TVDetail;
import com.ihfazh.moviecatalog.data.responses.TVListResponse;
import com.ihfazh.moviecatalog.data.responses.TVResultItem;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    private ApiService apiService;

    @Inject
    public RemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public interface DataSourceCallback<T>{
       void onSuccess(T response);
       void onError(String errorMessage);
    }

    public void listMovie(DataSourceCallback<List<MovieResultItem>> callback){
        apiService.listMovie().enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                callback.onSuccess(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                callback.onError(t.getMessage());

            }
        });
    }

    public void listTvShows(DataSourceCallback<List<TVResultItem>> callback){
        apiService.listTv().enqueue(new Callback<TVListResponse>() {
            @Override
            public void onResponse(Call<TVListResponse> call, Response<TVListResponse> response) {
                callback.onSuccess(response.body().getResults());
            }

            @Override
            public void onFailure(Call<TVListResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getMovieById(String id, DataSourceCallback<MovieDetail> callback){
        apiService.getMovie(id).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void getTvById(String id, DataSourceCallback<TVDetail> callback){
        apiService.getTv(id).enqueue(new Callback<TVDetail>() {
            @Override
            public void onResponse(Call<TVDetail> call, Response<TVDetail> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TVDetail> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
