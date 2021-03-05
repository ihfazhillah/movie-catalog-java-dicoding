package com.ihfazh.moviecatalog.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;
import com.ihfazh.moviecatalog.utils.DummyData;

import java.util.List;

import javax.inject.Inject;

public class DetailMovieViewModel extends ViewModel {
    private TMDBRepository repository;

    @Inject
    public DetailMovieViewModel(TMDBRepository repository) {
        this.repository = repository;
    }

    public LiveData<MovieEntity> getMovieById(String id) {
        return repository.getMovieById(id);
    }
}
