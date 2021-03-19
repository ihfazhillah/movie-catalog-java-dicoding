package com.ihfazh.moviecatalog.ui.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;
import com.ihfazh.moviecatalog.ui.favorites.FavoriteViewModel;
import com.ihfazh.moviecatalog.ui.home.HomeViewModel;
import com.ihfazh.moviecatalog.ui.movie.DetailMovieViewModel;
import com.ihfazh.moviecatalog.ui.tvshows.DetailTvShowViewModel;

import javax.inject.Inject;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final TMDBRepository repository;

    @Inject
    public ViewModelFactory(TMDBRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)){
            return (T) new HomeViewModel(repository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)){
            return (T) new DetailMovieViewModel(repository);
        } else if (modelClass.isAssignableFrom(DetailTvShowViewModel.class)){
            return (T) new DetailTvShowViewModel(repository);
        } else if (modelClass.isAssignableFrom(FavoriteViewModel.class)){
            return (T) new FavoriteViewModel(repository);
        }
        return super.create(modelClass);
    }
}
