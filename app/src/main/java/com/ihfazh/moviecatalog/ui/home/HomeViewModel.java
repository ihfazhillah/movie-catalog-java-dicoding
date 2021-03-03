package com.ihfazh.moviecatalog.ui.home;

import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.MovieEntity;
import com.ihfazh.moviecatalog.data.TvShowEntity;
import com.ihfazh.moviecatalog.utils.DummyData;

import java.util.List;

public class HomeViewModel extends ViewModel {
    public List<MovieEntity> loadMovies() {
        return DummyData.generateMovies();
    }

    public List<TvShowEntity> loadTvShows() {
        return DummyData.generateTvShows();
    }
}
