package com.ihfazh.moviecatalog.data.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.google.gson.Gson;
import com.ihfazh.moviecatalog.data.local.AppDatabase;
import com.ihfazh.moviecatalog.data.remote.MoviePagingSource;
import com.ihfazh.moviecatalog.data.remote.RemoteDataSource;
import com.ihfazh.moviecatalog.data.responses.MovieListResponse;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TMDBRepositoryTest {


    private TMDBRepository repository;

    @Mock
    RemoteDataSource remoteDataSource;

    @Mock
    AppDatabase localDataSource;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        repository = new TMDBRepository(remoteDataSource, localDataSource);
    }

    @Test
    public void getMovies() throws IOException {
        String responseString = getFileContent("popular_movies_200_response.json");
        MovieListResponse  response = new Gson().fromJson(responseString, MovieListResponse.class);

        MoviePagingSource movieSource = mock(MoviePagingSource.class);
        when(movieSource.getResponse(any(Response.class))).thenReturn(response.getResults());

        repository.getMovies();
        verify(movieSource).getResponse(any(Response.class));

        // method verify ini untuk memastikan, apakah listMovie ini bener bener terpanggil atau tidak.
        // ketika repository.getMovies dipanggil.
        verify(remoteDataSource).listMovie();
        assert Objects.requireNonNull(repository.getMovies().getValue()).size() > 1;
    }
//
//
//    @Test
//    public void getTvShows() {
//        Mockito.doAnswer(invocation -> {
//            String responseString = getFileContent("popular_tv_200_response.json");
//            TVListResponse  response = new Gson().fromJson(responseString, TVListResponse.class);
//            RemoteDataSource.DataSourceCallback callback = invocation.getArgument(0);
//            callback.onSuccess(response.getResults());
//            return null;
//        }).when(remoteDataSource).listTvShows(any(RemoteDataSource.DataSourceCallback.class));
//
//        repository.getTvShows();
//
//        verify(remoteDataSource).listTvShows(any());
//        List<TvShowEntity> tvShows = repository.getTvShows().getValue();
//        assert tvShows != null;
//        Assert.assertNotEquals(tvShows.size(), 0);
//    }
//
//    @Test
//    public void getMovieById() {
//        Mockito.doAnswer(invocation -> {
//            String responseString = getFileContent("detail_movie_200_response.json");
//            MovieDetail response = new Gson().fromJson(responseString, MovieDetail.class);
//            RemoteDataSource.DataSourceCallback callback =  invocation.getArgument(1);
//            callback.onSuccess(response);
//            return null;
//        }).when(remoteDataSource).getMovieById(eq("1"), any(RemoteDataSource.DataSourceCallback.class));
//
//        repository.getMovieById("1");
//
//        verify(remoteDataSource).getMovieById(eq("1"), any());
//        MovieEntity entity = repository.getMovieById("1").getValue();
//        Assert.assertNotNull(entity);
//        Assert.assertNotNull(entity.getId());
//    }
//
//    @Test
//    public void getTvById() {
//        Mockito.doAnswer(invocation -> {
//            String responseString = getFileContent("detail_tv_200_response.json");
//            TVDetail response = new Gson().fromJson(responseString, TVDetail.class);
//            RemoteDataSource.DataSourceCallback callback =  invocation.getArgument(1);
//            callback.onSuccess(response);
//            return null;
//        }).when(remoteDataSource).getTvById(eq("1"), any(RemoteDataSource.DataSourceCallback.class));
//
//        repository.getTvById("1");
//
//        verify(remoteDataSource).getTvById(eq("1"), any());
//        TvShowEntity entity = repository.getTvById("1").getValue();
//        Assert.assertNotNull(entity);
//        Assert.assertNotNull(entity.getId());
//    }
//
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