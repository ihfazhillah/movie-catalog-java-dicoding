package com.ihfazh.moviecatalog.ui.tvshows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.R;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.databinding.ActivityDetailTvShowBinding;
import com.ihfazh.moviecatalog.ui.favorites.FavoriteActivity;
import com.ihfazh.moviecatalog.ui.viewmodels.ViewModelFactory;
import com.ihfazh.moviecatalog.utils.TMDBUtils;
import com.ihfazh.moviecatalog.utils.dagger.ApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.DaggerApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApplicationModule;

import javax.inject.Inject;

public class DetailTvShowActivity extends AppCompatActivity {
    public static final String FROM_FAVORITE_PAGE = "is_from_favorite_page";
    private static final String TAG = "DetailTvShowActivity";

    public static final String TV_SHOW_TITLE = "tv_show_title";

    @Inject public ViewModelFactory factory;
    ApplicationComponent component;
    private Menu menu;
    DetailTvShowViewModel viewModel;
    boolean isFromFavoritePage;
    ActivityDetailTvShowBinding binding;

    private Observer<? super TvShowEntity> tvShowObserver = new Observer<TvShowEntity>() {
        @Override
        public void onChanged(TvShowEntity tvShow) {
            binding.title.setText(tvShow.getTitle());
            binding.overview.setText(tvShow.getOverview());

            binding.status.setText(tvShow.getStatus());
            binding.score.setText(tvShow.getScore());
            binding.type.setText(tvShow.getType());

            setBookmarkState(tvShow.isBookmarked());

            Glide.with(getApplicationContext())
                    .load(TMDBUtils.getFullImagePath(tvShow.getPoster_url()))
                    .into(binding.imgPoster);

            if (getSupportActionBar() != null){
                getSupportActionBar().setTitle(tvShow.getTitle());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityDetailTvShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);

        String tvShowTitle = getIntent().getStringExtra(TV_SHOW_TITLE);
        isFromFavoritePage = getIntent().getBooleanExtra(FROM_FAVORITE_PAGE, false);

        viewModel = new ViewModelProvider(this, factory).get(DetailTvShowViewModel.class);

        viewModel.setId(tvShowTitle);
        viewModel.tvShow.observe(this, tvShowObserver);

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

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onCreate: isFromFavoritePage" + isFromFavoritePage);
        if (isFromFavoritePage){
            // go to favorite page again
            Intent intent = new Intent(DetailTvShowActivity.this, FavoriteActivity.class);
            startActivity(intent);
            finish();
        } else {
            // go to main menu
            super.onBackPressed();
        }
    }


}