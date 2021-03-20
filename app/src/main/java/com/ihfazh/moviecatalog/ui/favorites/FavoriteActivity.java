package com.ihfazh.moviecatalog.ui.favorites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ihfazh.moviecatalog.R;
import com.ihfazh.moviecatalog.databinding.ActivityFavoriteBinding;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFavoriteBinding binding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FavoriteFragmentAdapter pagerAdapter = new FavoriteFragmentAdapter( getSupportFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);

        binding.tab.setupWithViewPager(binding.viewPager);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.daftar_favorite);
        }
    }


}