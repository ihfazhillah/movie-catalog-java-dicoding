package com.ihfazh.moviecatalog.ui.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.databinding.ActivityDetailMovieBinding;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String MOVIE_TITLE = "movie_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailMovieBinding binding = ActivityDetailMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String movieTitle = intent.getStringExtra(MOVIE_TITLE);

        DetailMovieViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailMovieViewModel.class);
        MovieEntity movie = viewModel.getMovieByTitle(movieTitle);

        binding.title.setText(movie.getTitle());
        binding.overview.setText(movie.getOverview());

        binding.duration.setText(movie.getLength());
        binding.status.setText(movie.getStatus());
        binding.budget.setText(movie.getBudget());
        binding.language.setText(movie.getLanguage());
        binding.score.setText(movie.getScore());

        Glide.with(this)
                .load(movie.getPosterUrl())
                .into(binding.imgPoster);
    }
}