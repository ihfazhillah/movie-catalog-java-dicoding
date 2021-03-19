package com.ihfazh.moviecatalog.data.remote;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseDataSource<Value, ResponseType> extends PageKeyedDataSource<Integer, Value> {
    @NonNull private ApiService apiService;

    public BaseDataSource(@NonNull ApiService apiService) {
        this.apiService = apiService;
    }

    @NonNull
    public ApiService getApiService() {
        return apiService;
    }

    protected abstract Call<ResponseType> getCall(String page);


    protected abstract List<Value> getResponse(Response<ResponseType> body);

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Value> callback) {
        getCall("1").enqueue(new Callback<ResponseType>() {
            @Override
            public void onResponse(Call<ResponseType> call, Response<ResponseType> response) {
                if (response.isSuccessful() && response.body() != null)
                    callback.onResult(getResponse(response), null, 1);
            }

            @Override
            public void onFailure(Call<ResponseType> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Value> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Value> callback) {

        getCall(String.valueOf(params.key)).enqueue(new Callback<ResponseType>() {
            @Override
            public void onResponse(Call<ResponseType> call, Response<ResponseType> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Integer nextPage = (getPage(response) < 1000) ? params.key + 1: null;
                    callback.onResult(getResponse(response), nextPage);
                }
            }

            @Override
            public void onFailure(Call<ResponseType> call, Throwable t) {

            }
        });
    }

    protected abstract Integer getPage(Response<ResponseType> response);
}
