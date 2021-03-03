package com.ihfazh.moviecatalog.ui.tvshows;

import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.TvShowEntity;
import com.ihfazh.moviecatalog.utils.DummyData;

import java.util.List;

public class DetailTvShowViewModel extends ViewModel {
    List<TvShowEntity> tvShows = DummyData.generateTvShows();

    public TvShowEntity getTvShowByTitle(String title) {
        for (TvShowEntity tvShow : tvShows) {
            if (tvShow.getTitle().equals(title)){
                return tvShow;
            }
        }
        return null;
    }
}
