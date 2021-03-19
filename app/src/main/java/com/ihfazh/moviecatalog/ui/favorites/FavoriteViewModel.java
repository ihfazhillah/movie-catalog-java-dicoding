package com.ihfazh.moviecatalog.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import javax.inject.Inject;

public class FavoriteViewModel extends ViewModel {
    private TMDBRepository repository;
    private static final String TAG = "FavoriteViewModel";

    @Inject
    public FavoriteViewModel(TMDBRepository repository) {
        this.repository = repository;
    }

    public LiveData<PagedList<MovieEntity>> loadMovies() {
        return new LivePagedListBuilder<>(repository.getBookmarkedMovie(), 20).build();
    }

    public LiveData<PagedList<TvShowEntity>> loadTvShows() {
        return new LivePagedListBuilder<>(repository.getBookmarkedTv(), 20).build();
    }
}
