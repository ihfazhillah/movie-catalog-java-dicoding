package com.ihfazh.moviecatalog.ui.favorites;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ihfazh.moviecatalog.R;
import com.ihfazh.moviecatalog.databinding.ActivityFavoriteBinding;
import com.ihfazh.moviecatalog.ui.viewmodels.ViewModelFactory;
import com.ihfazh.moviecatalog.utils.dagger.ApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.DaggerApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApplicationModule;
import com.ihfazh.moviecatalog.utils.sql.Sort;

import javax.inject.Inject;

public class FavoriteActivity extends AppCompatActivity {
    FavoriteViewModel viewModel;
    @Inject
    ViewModelFactory vMFactory;

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

        ApplicationComponent component =  DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
        component.inject(this);

        viewModel = new ViewModelProvider(this, vMFactory).get(FavoriteViewModel.class);
        viewModel.setSort(Sort.ASC);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_asc:
                viewModel.setSort(Sort.ASC);
                item.setChecked(true);
                break;
            case R.id.action_desc:
                viewModel.setSort(Sort.DESC);
                item.setChecked(true);
                break;
            case R.id.action_random:
                viewModel.setSort(Sort.RANDOM);
                item.setChecked(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}