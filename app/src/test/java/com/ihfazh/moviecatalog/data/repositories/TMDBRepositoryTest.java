package com.ihfazh.moviecatalog.data.repositories;

import android.os.SystemClock;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.espresso.IdlingRegistry;

import com.google.gson.Gson;
import com.ihfazh.moviecatalog.data.RemoteDataSource;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.responses.MovieDetail;
import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.data.responses.MovieResultItem;
import com.ihfazh.moviecatalog.utils.EspressoIdlingResources;
import com.ihfazh.moviecatalog.utils.TMDBUtils;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
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

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.Response.*;

@RunWith(MockitoJUnitRunner.class)
public class TMDBRepositoryTest {


    private TMDBRepository repository;

    @Mock
    RemoteDataSource remoteDataSource;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        repository = new TMDBRepository(remoteDataSource);
    }

    @Test
    public void getMovies() throws IOException {
        Mockito.doAnswer(invocation -> {
            String responseString = getFileContent("popular_movies_200_response.json");
            MovieListResponse listResponse = new Gson().fromJson(responseString, MovieListResponse.class);
            RemoteDataSource.DataSourceCallback<List<MovieResultItem>> callback = (RemoteDataSource.DataSourceCallback<List<MovieResultItem>>) invocation.getArgument(0);
            callback.onSuccess(listResponse.getResults());
            return null;
        }).when(remoteDataSource).listMovie(Mockito.any(RemoteDataSource.DataSourceCallback.class));
        
        repository.getMovies();
        assert Objects.requireNonNull(repository.getMovies().getValue()).size() > 1;
    }


    @Test
    public void getTvShows() {
    }

    @Test
    public void getMovieById() {
        Mockito.doAnswer(invocation -> {
            String responseString = getFileContent("detail_movie_200_response.json");
            MovieDetail response = new Gson().fromJson(responseString, MovieDetail.class);
            RemoteDataSource.DataSourceCallback callback = (RemoteDataSource.DataSourceCallback) invocation.getArgument(1);
            callback.onSuccess(response);
            return null;
        }).when(remoteDataSource).getMovieById(Mockito.eq("1"), Mockito.any(RemoteDataSource.DataSourceCallback.class));

        repository.getMovieById("1");
        MovieEntity entity = repository.getMovieById("1").getValue();
        Assert.assertNotNull(entity.getId());
    }

    @Test
    public void getTvById() {
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