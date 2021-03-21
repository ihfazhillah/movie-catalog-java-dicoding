package com.ihfazh.moviecatalog.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;
import com.ihfazh.moviecatalog.utils.sql.MovieSqlHelper;
import com.ihfazh.moviecatalog.utils.sql.Sort;
import com.ihfazh.moviecatalog.utils.sql.TvSqlHelper;

import javax.inject.Inject;

public class FavoriteViewModel extends ViewModel {
    private TMDBRepository repository;
    private static final String TAG = "FavoriteViewModel";
    MutableLiveData<Sort> sort = new MutableLiveData<>();

    @Inject
    public FavoriteViewModel(TMDBRepository repository) {
        this.repository = repository;
    }

    public LiveData<PagedList<MovieEntity>> loadMovies = Transformations.switchMap(
            sort,
            mSort -> repository.getBookmarkedMovie(MovieSqlHelper.getListBookmarked(mSort))
    );

    public LiveData<PagedList<TvShowEntity>> loadTvShows = Transformations.switchMap(
            sort,
            mSort -> repository.getBookmarkedTv(TvSqlHelper.getListBookmarked(mSort))

    );


    public void setSort(Sort sort) {
        this.sort.postValue(sort);
    }

    public MutableLiveData<Sort> getSort() {
        return sort;
    }
}
