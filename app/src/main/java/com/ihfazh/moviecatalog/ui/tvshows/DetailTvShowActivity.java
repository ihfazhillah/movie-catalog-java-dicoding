package com.ihfazh.moviecatalog.ui.tvshows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.databinding.ActivityDetailTvShowBinding;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String TV_SHOW_TITLE = "tv_show_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailTvShowBinding binding = ActivityDetailTvShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String tvShowTitle = getIntent().getStringExtra(TV_SHOW_TITLE);

        DetailTvShowViewModel viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(DetailTvShowViewModel.class);
        TvShowEntity tvShow = viewModel.getTvShowByTitle(tvShowTitle);


        binding.title.setText(tvShow.getTitle());
        binding.overview.setText(tvShow.getOverview());

        binding.status.setText(tvShow.getStatus());
        binding.score.setText(tvShow.getScore());
        binding.type.setText(tvShow.getType());

        Glide.with(this)
                .load(tvShow.getPoster_url())
                .into(binding.imgPoster);
    }
}