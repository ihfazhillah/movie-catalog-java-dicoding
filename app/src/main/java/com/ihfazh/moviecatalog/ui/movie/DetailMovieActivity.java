package com.ihfazh.moviecatalog.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.R;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.databinding.ActivityDetailMovieBinding;
import com.ihfazh.moviecatalog.ui.favorites.FavoriteActivity;
import com.ihfazh.moviecatalog.ui.viewmodels.ViewModelFactory;
import com.ihfazh.moviecatalog.utils.TMDBUtils;
import com.ihfazh.moviecatalog.utils.dagger.ApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.DaggerApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApplicationModule;

import javax.inject.Inject;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "movie_id";
    public static final String FROM_FAVORITE_PAGE = "from_favorite_page";
    private boolean fromFavoritePage;

    @Inject
    public ViewModelFactory factory;
    DetailMovieViewModel viewModel;
    ActivityDetailMovieBinding binding;
    Menu menu;
    String movieTitle;

    ApplicationComponent component;
    private final Observer<MovieEntity> movieObserver = new Observer<MovieEntity>() {
        @Override
        public void onChanged(MovieEntity movie) {
            binding.title.setText(movie.getTitle());
            binding.overview.setText(movie.getOverview());

            binding.duration.setText(movie.getLength());
            binding.status.setText(movie.getStatus());
            binding.budget.setText(movie.getBudget());
            binding.language.setText(movie.getLanguage());
            binding.score.setText(movie.getScore());

            Glide.with(getApplicationContext())
                    .load(TMDBUtils.getFullImagePath(movie.getPosterUrl()))
                    .into(binding.imgPoster);
            viewModel.setIsLoading(false);

            setBookmarkState(movie.isBookmarked());

            if (getSupportActionBar() != null){
                getSupportActionBar().setTitle(movie.getTitle());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getComponent();

        Intent intent = getIntent();
        movieTitle = intent.getStringExtra(MOVIE_ID);
        fromFavoritePage = intent.getBooleanExtra(FROM_FAVORITE_PAGE, false);

        viewModel = new ViewModelProvider(this, factory).get(DetailMovieViewModel.class);

        viewModel.setIsLoading(true);
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (!isLoading){
                binding.shimmerViewContainer.hideShimmer();
            }
        });

        viewModel.setMovieId(movieTitle);

        viewModel.movie.observe(this, movieObserver);

    }

    private void getComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
        component.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_bookmark){
            viewModel.setBookmark();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBookmarkState(boolean state){
        if (menu == null) return;
        MenuItem item = menu.findItem(R.id.action_bookmark);
        if (item == null) return;
        if (state){
            item.setIcon(R.drawable.bookmark_on);
        } else {
            item.setIcon(R.drawable.bookmark_off);
        }
    }

    @Override
    public void onBackPressed() {
        if (fromFavoritePage){
            // should keep track the page button
            Intent intent = new Intent(DetailMovieActivity.this, FavoriteActivity.class);
            startActivity(intent);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return true;
    }
}