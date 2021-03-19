package com.ihfazh.moviecatalog.data.datasources;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;

public interface TMDBDataSource {
    LiveData<PagedList<MovieEntity>> getMovies();
    LiveData<PagedList<TvShowEntity>> getTvShows();
}
