package com.ihfazh.moviecatalog.data.repositories;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.local.AppDatabase;
import com.ihfazh.moviecatalog.data.remote.RemoteDataSource;

import org.jetbrains.annotations.NotNull;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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
        DataSource.Factory<Integer, MovieEntity> factory = mock(DataSource.Factory.class);
        when(remoteDataSource.listMovie()).thenReturn(factory);

        repository.getMovies();

        // method verify ini untuk memastikan, apakah listMovie ini bener bener terpanggil atau tidak.
        // ketika repository.getMovies dipanggil.
        verify(remoteDataSource).listMovie();
    }


    @Test
    public void getTvShows() {
        DataSource.Factory<Integer, TvShowEntity> factory = mock(DataSource.Factory.class);
        when(remoteDataSource.listTvShows()).thenReturn(factory);

        repository.getTvShows();

        // method verify ini untuk memastikan, apakah listMovie ini bener bener terpanggil atau tidak.
        // ketika repository.getMovies dipanggil.
        verify(remoteDataSource).listTvShows();
    }

    @Test
    public void getMovieById() {
        MutableLiveData<MovieEntity> liveData = new MutableLiveData<>();
        Mockito.when(localDataSource.getMovie("1")).thenReturn(liveData);
        repository.getMovieById("1");
        verify(localDataSource).getMovie(eq("1"));
        verify(remoteDataSource, never()).getMovieById(eq("1"), any());
    }


    @Test
    public void getTvById() {
        MutableLiveData<TvShowEntity> liveData = new MutableLiveData<>();
        Mockito.when(localDataSource.getTv("1")).thenReturn(liveData);
        repository.getTvById("1");
        verify(localDataSource).getTv(eq("1"));
        verify(remoteDataSource, never()).getTvById(eq("1"), any());
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