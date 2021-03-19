package com.ihfazh.moviecatalog.ui.tvshows;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import javax.inject.Inject;

public class DetailTvShowViewModel extends ViewModel {
    private TMDBRepository repository;

    private MutableLiveData<String> id = new MutableLiveData<>();

    public LiveData<TvShowEntity> tvShow = Transformations.switchMap(id, id-> repository.getTvById(id));

    @Inject
    public DetailTvShowViewModel(TMDBRepository repository) {
        this.repository = repository;
    }

    public void setId(String id) {
        this.id.setValue(id);
    }

    public void setBookmark() {
        TvShowEntity tv = tvShow.getValue();
        if (tv != null){
            boolean newState = !tv.isBookmarked();
            tv.setBookmarked(newState);
            repository.updateTv(tv);
        }
    }
}
