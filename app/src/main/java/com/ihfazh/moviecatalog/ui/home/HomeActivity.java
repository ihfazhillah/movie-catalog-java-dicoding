package com.ihfazh.moviecatalog.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ihfazh.moviecatalog.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HomePagerAdapter pagerAdapter = new HomePagerAdapter( getSupportFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);

        binding.tab.setupWithViewPager(binding.viewPager);
    }
}