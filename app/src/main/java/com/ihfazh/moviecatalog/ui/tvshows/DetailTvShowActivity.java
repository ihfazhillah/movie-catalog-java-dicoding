package com.ihfazh.moviecatalog.ui.tvshows;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.R;
import com.ihfazh.moviecatalog.databinding.ActivityDetailTvShowBinding;
import com.ihfazh.moviecatalog.ui.viewmodels.ViewModelFactory;
import com.ihfazh.moviecatalog.utils.TMDBUtils;
import com.ihfazh.moviecatalog.utils.dagger.ApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.DaggerApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApplicationModule;

import javax.inject.Inject;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String TV_SHOW_TITLE = "tv_show_title";

    @Inject public ViewModelFactory factory;
    ApplicationComponent component;
    private Menu menu;
    DetailTvShowViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailTvShowBinding binding = ActivityDetailTvShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);

        String tvShowTitle = getIntent().getStringExtra(TV_SHOW_TITLE);

        viewModel = new ViewModelProvider(this, factory).get(DetailTvShowViewModel.class);

        viewModel.setId(tvShowTitle);
        viewModel.tvShow.observe(this, tvShow -> {
            binding.title.setText(tvShow.getTitle());
            binding.overview.setText(tvShow.getOverview());

            binding.status.setText(tvShow.getStatus());
            binding.score.setText(tvShow.getScore());
            binding.type.setText(tvShow.getType());

            setBookmarkState(tvShow.isBookmarked());

            Glide.with(this)
                    .load(TMDBUtils.getFullImagePath(tvShow.getPoster_url()))
                    .into(binding.imgPoster);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    private void setBookmarkState(boolean bookmarked) {
        if (menu == null) {
            return;
        }
        MenuItem item = menu.findItem(R.id.action_bookmark);
        if (item == null) {
            return;
        }

        if (bookmarked){
            item.setIcon(R.drawable.bookmark_on);
        } else {
            item.setIcon(R.drawable.bookmark_off);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_bookmark){
            viewModel.setBookmark();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}