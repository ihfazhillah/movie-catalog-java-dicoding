package com.ihfazh.moviecatalog.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;

@Database(entities = {MovieEntity.class, TvShowEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    public abstract TVShowDao tvDao();
}
