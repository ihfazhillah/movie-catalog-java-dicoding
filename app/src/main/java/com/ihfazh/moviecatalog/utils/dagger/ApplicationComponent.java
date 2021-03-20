package com.ihfazh.moviecatalog.utils.dagger;

import com.ihfazh.moviecatalog.ui.favorites.FavoriteActivity;
import com.ihfazh.moviecatalog.ui.home.movies.MovieListFragment;
import com.ihfazh.moviecatalog.ui.home.tvshows.TvShowsFragment;
import com.ihfazh.moviecatalog.ui.movie.DetailMovieActivity;
import com.ihfazh.moviecatalog.ui.tvshows.DetailTvShowActivity;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApiModule;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApplicationModule;
import com.ihfazh.moviecatalog.utils.dagger.modules.DatabaseModule;
import com.ihfazh.moviecatalog.utils.dagger.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApiModule.class, RepositoryModule.class, ApplicationModule.class, DatabaseModule.class})
public interface ApplicationComponent {
    void inject(MovieListFragment view);
    void inject(TvShowsFragment view);
    void inject(DetailMovieActivity view);
    void inject(DetailTvShowActivity view);

    void inject(com.ihfazh.moviecatalog.ui.favorites.movies.MovieListFragment movieListFragment);
    void inject(com.ihfazh.moviecatalog.ui.favorites.tvshows.TvShowsFragment tvShowsFragment);

    void inject(FavoriteActivity favoriteActivity);
}
