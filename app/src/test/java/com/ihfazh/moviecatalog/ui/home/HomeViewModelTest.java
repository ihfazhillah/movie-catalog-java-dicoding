package com.ihfazh.moviecatalog.ui.home;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;
import com.ihfazh.moviecatalog.utils.DummyData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeViewModelTest {

    private HomeViewModel viewModel;

    // butuh ini, untuk baypass main thread check dan langsung menjalankan semua task di thread
    // tidak ada task lagi async.
    // gunakan ini untuk class yang di dalamnya ada liveData.setValue atau liveData.postValue
    // https://medium.com/pxhouse/unit-testing-with-mutablelivedata-22b3283a7819

    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock private TMDBRepository repository;
    @Mock private Observer<List<MovieEntity>> moviesObserver;
    @Mock private Observer<List<TvShowEntity>> tvShowsObserver;

    @Before
    public void setUp() throws Exception {
        viewModel = new HomeViewModel(repository);
    }

    @Test
    public void testLoadMovies(){
        List<MovieEntity> dummyMovies = DummyData.generateMovies();
        MutableLiveData<List<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        Mockito.when(repository.getMovies()).thenReturn(movies);
        List<MovieEntity> movieEntities = viewModel.loadMovies().getValue();
        assertNotNull(movieEntities);
        assertEquals(10, movieEntities.size());

        viewModel.loadMovies().observeForever(moviesObserver);
        Mockito.verify(moviesObserver).onChanged(dummyMovies);
    }

    @Test
    public void testLoadTvShows(){
        List<TvShowEntity> dummyTvShows = DummyData.generateTvShows();
        MutableLiveData<List<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTvShows);

        Mockito.when(repository.getTvShows()).thenReturn(tvShows);

        List<TvShowEntity> tvShowEntities = viewModel.loadTvShows().getValue();
        assertNotNull(tvShowEntities);
        assertEquals(10, tvShowEntities.size());

        viewModel.loadTvShows().observeForever(tvShowsObserver);
        Mockito.verify(tvShowsObserver).onChanged(dummyTvShows);
    }
}