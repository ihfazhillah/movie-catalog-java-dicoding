package com.ihfazh.moviecatalog.data.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.google.gson.Gson;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.remote.RemoteDataSource;
import com.ihfazh.moviecatalog.data.responses.MovieDetail;
import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
import com.ihfazh.moviecatalog.data.responses.TVDetail;
import com.ihfazh.moviecatalog.data.responses.TVListResponse;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class TMDBRepositoryTest {


    private TMDBRepository repository;

    @Mock
    RemoteDataSource remoteDataSource;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        repository = new TMDBRepository(remoteDataSource);
    }

    @Test
    public void getMovies() {
        Mockito.doAnswer(invocation -> {
            String responseString = getFileContent("popular_movies_200_response.json");
            MovieListResponse listResponse = new Gson().fromJson(responseString, MovieListResponse.class);
            RemoteDataSource.DataSourceCallback<List<MovieResultItem>> callback =  invocation.getArgument(0);
            callback.onSuccess(listResponse.getResults());
            return null;
        }).when(remoteDataSource).listMovie(Mockito.any(RemoteDataSource.DataSourceCallback.class));
        
        repository.getMovies();

        // method verify ini untuk memastikan, apakah listMovie ini bener bener terpanggil atau tidak.
        // ketika repository.getMovies dipanggil.
        Mockito.verify(remoteDataSource).listMovie(Mockito.any(RemoteDataSource.DataSourceCallback.class));
        assert Objects.requireNonNull(repository.getMovies().getValue()).size() > 1;
    }


    @Test
    public void getTvShows() {
        Mockito.doAnswer(invocation -> {
            String responseString = getFileContent("popular_tv_200_response.json");
            TVListResponse  response = new Gson().fromJson(responseString, TVListResponse.class);
            RemoteDataSource.DataSourceCallback callback = invocation.getArgument(0);
            callback.onSuccess(response.getResults());
            return null;
        }).when(remoteDataSource).listTvShows(Mockito.any(RemoteDataSource.DataSourceCallback.class));

        repository.getTvShows();

        Mockito.verify(remoteDataSource).listTvShows(Mockito.any());
        List<TvShowEntity> tvShows = repository.getTvShows().getValue();
        assert tvShows != null;
        Assert.assertNotEquals(tvShows.size(), 0);
    }

    @Test
    public void getMovieById() {
        Mockito.doAnswer(invocation -> {
            String responseString = getFileContent("detail_movie_200_response.json");
            MovieDetail response = new Gson().fromJson(responseString, MovieDetail.class);
            RemoteDataSource.DataSourceCallback callback =  invocation.getArgument(1);
            callback.onSuccess(response);
            return null;
        }).when(remoteDataSource).getMovieById(Mockito.eq("1"), Mockito.any(RemoteDataSource.DataSourceCallback.class));

        repository.getMovieById("1");

        Mockito.verify(remoteDataSource).getMovieById(Mockito.eq("1"), Mockito.any());
        MovieEntity entity = repository.getMovieById("1").getValue();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
    }

    @Test
    public void getTvById() {
        Mockito.doAnswer(invocation -> {
            String responseString = getFileContent("detail_tv_200_response.json");
            TVDetail response = new Gson().fromJson(responseString, TVDetail.class);
            RemoteDataSource.DataSourceCallback callback =  invocation.getArgument(1);
            callback.onSuccess(response);
            return null;
        }).when(remoteDataSource).getTvById(Mockito.eq("1"), Mockito.any(RemoteDataSource.DataSourceCallback.class));

        repository.getTvById("1");

        Mockito.verify(remoteDataSource).getTvById(Mockito.eq("1"), Mockito.any());
        TvShowEntity entity = repository.getTvById("1").getValue();
        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
    }

    @NotNull
    private String getFileContent(String fileName) throws IOException {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder file = new StringBuilder();
        String line = reader.readLine();
        while (line != null){
            file.append(line);
            line = reader.readLine();
        }
        return file.toString();
    }
}