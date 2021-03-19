package com.ihfazh.moviecatalog.data.remote;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePagingSource extends PageKeyedDataSource<Integer, MovieResultItem> {
    private static final String TAG = "MoviePagingSource";
    private ApiService service;

    @Inject
    public MoviePagingSource(ApiService service) {
        this.service = service;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MovieResultItem> callback) {
        service.listMovie("1").enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    callback.onResult(response.body().getResults(), null, response.body().getPage() + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieResultItem> callback) {
        service.listMovie(String.valueOf(params.key)).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                // if the current page is greater than one
                // we are decrementing the page number
                // else there are no previous page
                Integer adjacentKey = (params.key > 1) ? params.key - 1: null;
                if (response.body() != null){
                    callback.onResult(response.body().getResults(), adjacentKey);
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieResultItem> callback) {
        service.listMovie(String.valueOf(params.key)).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.body() != null){
                    Integer nextPage = (params.key < response.body().getTotalPages()) ? params.key + 1 : null;
                    callback.onResult(response.body().getResults(), nextPage);
                }

            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {

            }
        });

    }
}
