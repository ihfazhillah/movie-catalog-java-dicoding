package com.ihfazh.moviecatalog.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import javax.inject.Inject;

public class DetailMovieViewModel extends ViewModel {
    private TMDBRepository repository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    @Inject
    public DetailMovieViewModel(TMDBRepository repository) {
        this.repository = repository;
    }

    public LiveData<MovieEntity> getMovieById(String id) {
        return repository.getMovieById(id);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(Boolean isLoading) {
        this.isLoading.postValue(isLoading);
    }
}
