package com.ihfazh.moviecatalog.utils.dagger;

import com.ihfazh.moviecatalog.ui.home.movies.MovieListFragment;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiModule;
import com.ihfazh.moviecatalog.utils.dagger.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApiModule.class, RepositoryModule.class})
public interface ApplicationComponent {
    void inject(MovieListFragment view);
}
