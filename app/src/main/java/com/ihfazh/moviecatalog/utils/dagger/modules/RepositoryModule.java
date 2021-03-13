package com.ihfazh.moviecatalog.utils.dagger.modules;

import com.ihfazh.moviecatalog.data.RemoteDataSource;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;

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
    TMDBRepository provideRepository(RemoteDataSource remoteDataSource){
        return new TMDBRepository(remoteDataSource);
    }
}
