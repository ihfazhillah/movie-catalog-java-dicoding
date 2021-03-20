package com.ihfazh.moviecatalog.data.remote;

import androidx.annotation.NonNull;

import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

public class MoviePagingSource extends BaseDataSource<MovieResultItem, MovieListResponse>{

    @Inject
    public MoviePagingSource(@NonNull ApiService apiService) {
        super(apiService);
    }

    @Override
    public Call<MovieListResponse> getCall(String page) {
        return getApiService().listMovie(page);
    }

    @Override
    public List<MovieResultItem> getResponse(Response<MovieListResponse> body) {
        assert body.body() != null;
        return body.body().getResults();
    }

    @Override
    protected Integer getPage(Response<MovieListResponse> response) {
        assert response.body() != null;
        return response.body().getPage();
    }
}
