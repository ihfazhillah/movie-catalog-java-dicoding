package com.ihfazh.moviecatalog.data.remote;

import androidx.annotation.NonNull;

import com.ihfazh.moviecatalog.data.responses.TVListResponse;
import com.ihfazh.moviecatalog.data.responses.TVResultItem;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class TvPagingDataSource extends BaseDataSource<TVResultItem, TVListResponse>{

    @Inject
    public TvPagingDataSource(@NonNull ApiService apiService) {
        super(apiService);
    }

    @Override
    protected Call<TVListResponse> getCall(String page) {
        return getApiService().listTv(page);
    }

    @Override
    protected List<TVResultItem> getResponse(Response<TVListResponse> body) {
        assert body.body() != null;
        return body.body().getResults();
    }

    @Override
    protected Integer getPage(Response<TVListResponse> response) {
        assert response.body() != null;
        return response.body().getPage();
    }
}
