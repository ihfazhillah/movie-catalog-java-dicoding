package com.ihfazh.moviecatalog.data.remote;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.ihfazh.moviecatalog.data.responses.TVListResponse;
import com.ihfazh.moviecatalog.data.responses.TVResultItem;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvPagingDataSource extends PageKeyedDataSource<Integer, TVResultItem> {
    @NonNull private ApiService apiService;

    @Inject
    public TvPagingDataSource(@NonNull ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, TVResultItem> callback) {
        apiService.listTv("1").enqueue(new Callback<TVListResponse>() {
            @Override
            public void onResponse(Call<TVListResponse> call, Response<TVListResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                    callback.onResult(response.body().getResults(), null, 1);
            }

            @Override
            public void onFailure(Call<TVListResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, TVResultItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, TVResultItem> callback) {
        apiService.listTv(String.valueOf(params.key)).enqueue(new Callback<TVListResponse>() {
            @Override
            public void onResponse(Call<TVListResponse> call, Response<TVListResponse> response) {
                if (response.body() != null){
                    Integer nextPage = (response.body().getPage() < 1000) ? params.key + 1: null;
                    callback.onResult(response.body().getResults(), nextPage);
                }

            }

            @Override
            public void onFailure(Call<TVListResponse> call, Throwable t) {

            }
        });

    }
}
