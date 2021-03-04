package com.ihfazh.moviecatalog.utils.dagger.modules;

import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.TVListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("tv/popular")
    Call<TVListResponse> listTv();

    @GET("movie/popular")
    Call<MovieListResponse> listMovie();

    // TODO: detail
}
