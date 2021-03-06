package com.ihfazh.moviecatalog.data.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.ihfazh.moviecatalog.data.entities.TvShowEntity;

@Dao
public interface TVShowDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TvShowEntity entity);

    @Update
    void update(TvShowEntity entity);

    @Query("select * from tv_show where bookmarked = 1")
    DataSource.Factory<Integer, TvShowEntity> getBookmarkedTvShows();

    @Query("select * from tv_show where id = :id")
    LiveData<TvShowEntity> getTv(String id);

    @RawQuery(observedEntities = TvShowEntity.class)
    DataSource.Factory<Integer, TvShowEntity> getBookmarkedTvShowsSort(SimpleSQLiteQuery query);
}
