package com.ihfazh.moviecatalog.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {
    private TMDBRepository repository;
    private static final String TAG = "HomeViewModel";

    @Inject
    public HomeViewModel(TMDBRepository repository) {
        this.repository = repository;
    }

    public LiveData<PagedList<MovieEntity>> loadMovies() {
        Log.d(TAG, "loadMovies: ");
        return repository.getMovies();
    }

    public LiveData<List<TvShowEntity>> loadTvShows() {
        return repository.getTvShows();
    }
}
