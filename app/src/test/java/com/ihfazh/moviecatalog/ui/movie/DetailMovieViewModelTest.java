package com.ihfazh.moviecatalog.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailMovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    DetailMovieViewModel viewModel;
    List<MovieEntity> movies = DummyData.generateMovies();
    MovieEntity firstMovie = movies.get(0);

    @Mock
    Observer<MovieEntity> observer;

    @Mock
    private TMDBRepository repository;

    @Before
    public void setup(){
        viewModel = new DetailMovieViewModel(repository);
        viewModel.setMovieId(firstMovie.getId());
    }

    @Test
    public void testGetMovieByTitle(){
        MutableLiveData<MovieEntity> movieEntity = new MutableLiveData<>();
        movieEntity.setValue(firstMovie);
        when(repository.getMovieById(firstMovie.getId())).thenReturn(movieEntity);

        viewModel.movie.observeForever(observer);
        Mockito.verify(observer).onChanged(firstMovie);
    }

}