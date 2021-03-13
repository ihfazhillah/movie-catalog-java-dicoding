package com.ihfazh.moviecatalog.ui.movie;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.databinding.ActivityDetailMovieBinding;
import com.ihfazh.moviecatalog.ui.viewmodels.ViewModelFactory;
import com.ihfazh.moviecatalog.utils.TMDBUtils;
import com.ihfazh.moviecatalog.utils.dagger.ApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.DaggerApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApplicationModule;

import javax.inject.Inject;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "movie_id";
    @Inject
    public ViewModelFactory factory;

    ApplicationComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailMovieBinding binding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getComponent();

        Intent intent = getIntent();
        String movieTitle = intent.getStringExtra(MOVIE_ID);

        DetailMovieViewModel viewModel = new ViewModelProvider(this, factory).get(DetailMovieViewModel.class);

        viewModel.setIsLoading(true);
        viewModel.getIsLoading().observe(this, isLoading -> {
            if (!isLoading){
                binding.shimmerViewContainer.hideShimmer();
            }
        });

        viewModel.getMovieById(movieTitle).observe(this, movie -> {
            binding.title.setText(movie.getTitle());
            binding.overview.setText(movie.getOverview());

            binding.duration.setText(movie.getLength());
            binding.status.setText(movie.getStatus());
            binding.budget.setText(movie.getBudget());
            binding.language.setText(movie.getLanguage());
            binding.score.setText(movie.getScore());

            Glide.with(this)
                    .load(TMDBUtils.getFullImagePath(movie.getPosterUrl()))
                    .into(binding.imgPoster);
            viewModel.setIsLoading(false);

        });

    }

    private void getComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
        component.inject(this);
    }
}