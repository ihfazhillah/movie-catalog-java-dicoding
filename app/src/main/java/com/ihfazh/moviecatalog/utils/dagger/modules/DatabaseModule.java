package com.ihfazh.moviecatalog.utils.dagger.modules;

import android.content.Context;

import com.ihfazh.moviecatalog.data.local.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(Context context){
        return AppDatabase.getInstance(context);
    };
}
