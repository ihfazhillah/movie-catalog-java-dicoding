package com.ihfazh.moviecatalog.utils.dagger.modules;

import com.ihfazh.moviecatalog.data.responses.MovieDetail;
import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.TVDetail;
import com.ihfazh.moviecatalog.data.responses.TVListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("tv/popular")
    Call<TVListResponse> listTv(@Query("page") String page);

    @GET("movie/popular")
    Call<MovieListResponse> listMovie(@Query("page") String page);

    @GET("movie/{id}")
    Call<MovieDetail> getMovie(@Path("id") String id);

    @GET("tv/{id}")
    Call<TVDetail> getTv(@Path("id") String id);
}
