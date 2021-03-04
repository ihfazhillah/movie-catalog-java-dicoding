package com.ihfazh.moviecatalog.ui.movie;

import androidx.lifecycle.ViewModel;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.utils.DummyData;

import java.util.List;

public class DetailMovieViewModel extends ViewModel {
    List<MovieEntity> movies = DummyData.generateMovies();

    public MovieEntity getMovieByTitle(String title) {
        for (MovieEntity movie : movies) {
            if (movie.getTitle().equals(title)){
                return movie;
            }
        }
        return null;
    }
}
