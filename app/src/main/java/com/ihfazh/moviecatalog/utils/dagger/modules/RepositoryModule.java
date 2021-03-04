package com.ihfazh.moviecatalog.utils.dagger.modules;

import android.content.Context;

import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

import java.util.prefs.AbstractPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    TMDBRepository provideRepository(ApiService apiService){
        return new TMDBRepository(apiService);
    }
}
