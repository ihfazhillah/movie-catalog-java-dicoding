package com.ihfazh.moviecatalog.ui.home;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HomeViewModelTest {

    private HomeViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        viewModel = new HomeViewModel();
    }

    @Test
    public void testLoadMovies(){
        List<MovieEntity> movieEntities = viewModel.loadMovies();
        assertNotNull(movieEntities);

        assertEquals(10, movieEntities.size());
    }

    @Test
    public void testLoadTvShows(){
        List<TvShowEntity> tvShows = viewModel.loadTvShows();
        assertNotNull(tvShows);
        assertEquals(10, tvShows.size());
    }
}