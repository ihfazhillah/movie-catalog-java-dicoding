package com.ihfazh.moviecatalog.data.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieEntity movieEntity);

    @Update
    void update(MovieEntity movieEntity);

    @Query("select * from movie where bookmarked = 1")
    DataSource.Factory<Integer, MovieEntity> getBookmarkedMovie();

    @Query("select * from movie where id = :id")
    LiveData<MovieEntity> getMovie(String id);

    @RawQuery(observedEntities = MovieEntity.class)
    DataSource.Factory<Integer, MovieEntity> getBookmarkedMovieSort(SupportSQLiteQuery query);
}
