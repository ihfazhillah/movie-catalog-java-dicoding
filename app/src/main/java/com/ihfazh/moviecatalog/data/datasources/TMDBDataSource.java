package com.ihfazh.moviecatalog.data.datasources;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;

import java.util.List;

public interface TMDBDataSource {
    LiveData<PagedList<MovieEntity>> getMovies();
    LiveData<List<TvShowEntity>> getTvShows();
}
