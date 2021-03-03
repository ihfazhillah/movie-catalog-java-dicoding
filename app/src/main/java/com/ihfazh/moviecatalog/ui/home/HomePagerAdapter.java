package com.ihfazh.moviecatalog.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ihfazh.moviecatalog.ui.home.movies.MovieListFragment;
import com.ihfazh.moviecatalog.ui.home.tvshows.TvShowsFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    public HomePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return MovieListFragment.newInstance();
            case 1:
                return TvShowsFragment.newInstance();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Movies";
            case 1:
                return "TV Shows";
        }
        return null;
    }
}
