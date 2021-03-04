package com.ihfazh.moviecatalog.ui.movie;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.utils.DummyData;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DetailMovieViewModelTest {
    DetailMovieViewModel viewModel;
    List<MovieEntity> movies = DummyData.generateMovies();
    MovieEntity firstMovie = movies.get(0);

    @Before
    public void setup(){
        viewModel = new DetailMovieViewModel();
    }

    @Test
    public void testGetMovieByTitle(){
        MovieEntity movie = viewModel.getMovieByTitle(firstMovie.getTitle());
        assertNotNull(movie);
        assertEquals(firstMovie.getTitle(), movie.getTitle());
    }

}