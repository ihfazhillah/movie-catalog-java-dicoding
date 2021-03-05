package com.ihfazh.moviecatalog.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private TMDBRepository repository;

    @Inject
    public HomeViewModel(TMDBRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<MovieEntity>> loadMovies() {
        return repository.getMovies();
    }

    public LiveData<List<TvShowEntity>> loadTvShows() {
        return repository.getTvShows();
    }
}
