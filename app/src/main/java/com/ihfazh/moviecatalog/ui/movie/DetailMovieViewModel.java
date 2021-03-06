package com.ihfazh.moviecatalog.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import javax.inject.Inject;

public class DetailMovieViewModel extends ViewModel {
    private static final String TAG = "DetailMovieViewModel";
    private TMDBRepository repository;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<String> movieTitle = new MutableLiveData<>();
    public LiveData<MovieEntity> movie = Transformations.switchMap(
            movieTitle, movieId -> repository.getMovieById(movieId)
    );

    @Inject
    public DetailMovieViewModel(TMDBRepository repository) {
        this.repository = repository;
    }


    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(Boolean isLoading) {
        this.isLoading.postValue(isLoading);
    }

    public void setBookmark(){
        MovieEntity entity = movie.getValue();
        if (entity != null){
            boolean newState = !entity.isBookmarked();
            repository.setBookmark(entity, newState);
        }
    }

    public void setMovieId(String movieTitle) {
        this.movieTitle.postValue(movieTitle);
    }
}
