package com.ihfazh.moviecatalog.ui.home;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeViewModelTest {

    private HomeViewModel viewModel;

    // butuh ini, untuk baypass main thread check dan langsung menjalankan semua task di thread
    // tidak ada task lagi async.
    // gunakan ini untuk class yang di dalamnya ada liveData.setValue atau liveData.postValue
    // https://medium.com/pxhouse/unit-testing-with-mutablelivedata-22b3283a7819

    @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock private TMDBRepository repository;
    @Mock private Observer<PagedList<MovieEntity>> moviesObserver;
    @Mock private Observer<PagedList<TvShowEntity>> tvShowsObserver;

    @Before
    public void setUp() throws Exception {
        viewModel = new HomeViewModel(repository);
    }

    @Test
    public void testLoadMovies(){
        PagedList<MovieEntity> dummyMovies = mock(PagedList.class);
        MutableLiveData<PagedList<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(repository.getMovies()).thenReturn(movies);
        List<MovieEntity> movieEntities = viewModel.loadMovies().getValue();
        verify(repository).getMovies();
        assertNotNull(movieEntities);

        when(movieEntities.size()).thenReturn(10);
        assertEquals(10, movieEntities.size());
        verify(movieEntities).size();

        viewModel.loadMovies().observeForever(moviesObserver);
        Mockito.verify(moviesObserver).onChanged(dummyMovies);
    }

    @Test
    public void testLoadTvShows(){
        PagedList<TvShowEntity> dummyTvShows = mock(PagedList.class);
        MutableLiveData<PagedList<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTvShows);

        when(repository.getTvShows()).thenReturn(tvShows);
        List<TvShowEntity> tvShowEntities = viewModel.loadTvShows().getValue();
        assertNotNull(tvShowEntities);
        verify(repository).getTvShows();

        when(tvShowEntities.size()).thenReturn(10);
        assertEquals(10, tvShowEntities.size());
        verify(tvShowEntities).size();

        viewModel.loadTvShows().observeForever(tvShowsObserver);
        Mockito.verify(tvShowsObserver).onChanged(dummyTvShows);
    }
}