package com.ihfazh.moviecatalog.ui.tvshows;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import javax.inject.Inject;

public class DetailTvShowViewModel extends ViewModel {
    private TMDBRepository repository;

    @Inject
    public DetailTvShowViewModel(TMDBRepository repository) {
        this.repository = repository;
    }

    public LiveData<TvShowEntity> getTvShowById(String title) {
        return repository.getTvById(title);
    }
}
