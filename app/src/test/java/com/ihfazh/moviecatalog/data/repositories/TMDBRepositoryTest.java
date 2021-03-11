package com.ihfazh.moviecatalog.data.repositories;

import android.os.SystemClock;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.espresso.IdlingRegistry;

import com.ihfazh.moviecatalog.data.RemoteDataSource;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.responses.MovieListResponse;
import com.ihfazh.moviecatalog.utils.EspressoIdlingResources;
import com.ihfazh.moviecatalog.utils.TMDBUtils;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiService;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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


    private MockWebServer server;
    private TMDBRepository repository;
    private ApiService service;

    @Mock
    EspressoIdlingResources espressoIdlingResources;
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        TMDBUtils.BASE_URL = server.url("/").toString();

        service = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TMDBUtils.BASE_URL)
                .build()
                .create(ApiService.class);

        RemoteDataSource remoteDataSource = new RemoteDataSource(service);
        repository = new TMDBRepository(remoteDataSource);
//        IdlingRegistry.getInstance().register(EspressoIdlingResources.getEspressoIdlingResource());

    }

    @After
    public void shutDown() throws IOException {
        server.shutdown();
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getEspressoIdlingResource());
    }

    @Test
    public void getMovies() throws IOException {

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

    @Test
    public void getTvShows() {
    }

    @Test
    public void getMovieById() {
    }

    @Test
    public void getTvById() {
    }
}