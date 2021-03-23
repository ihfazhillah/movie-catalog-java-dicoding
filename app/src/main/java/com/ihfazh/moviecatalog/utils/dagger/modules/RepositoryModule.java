package com.ihfazh.moviecatalog.utils.dagger.modules;

import com.ihfazh.moviecatalog.data.local.AppDatabase;
import com.ihfazh.moviecatalog.data.remote.RemoteDataSource;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;
import com.ihfazh.moviecatalog.utils.AppExecutors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    RemoteDataSource provideRemoteDataSource(ApiService apiService){
        return new RemoteDataSource(apiService);
    }

    @Singleton
    @Provides
    AppExecutors provideExecutor(){
        return new AppExecutors();
    }

    @Singleton
    @Provides
    TMDBRepository provideRepository(RemoteDataSource remoteDataSource, AppDatabase db, AppExecutors executors){
        return new TMDBRepository(remoteDataSource, db, executors);
    }
}
