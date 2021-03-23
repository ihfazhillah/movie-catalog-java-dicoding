package com.ihfazh.moviecatalog.data.local;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.utils.TMDBUtils;

@Database(entities = {MovieEntity.class, TvShowEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;


    public static AppDatabase getInstance(Context context) {
        if (instance == null){
            synchronized (AppDatabase.class){
                if (instance == null){
                    if (TMDBUtils.memoryDb){
                        instance = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                                .build();
                    } else {
                        instance = Room.databaseBuilder(context, AppDatabase.class, "movie-catalog.db")
                                .build();
                    }
                }
            }
        }
        return instance;
    }

    public abstract MovieDao movieDao();
    public abstract TVShowDao tvDao();

    public LiveData<MovieEntity> getMovie(String id) {
        return this.movieDao().getMovie(id);
    }

    public LiveData<TvShowEntity> getTv(String id) {
        return this.tvDao().getTv(id);
    }

    public DataSource.Factory<Integer, MovieEntity> getBookmarkedMovie(SupportSQLiteQuery query) {
        return movieDao().getBookmarkedMovieSort(query);

    }

    public DataSource.Factory<Integer, TvShowEntity> getBookmarkedTv(SimpleSQLiteQuery query) {
        return tvDao().getBookmarkedTvShowsSort(query);
    }
}
