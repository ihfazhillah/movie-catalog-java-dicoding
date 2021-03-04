package com.ihfazh.moviecatalog.ui.home.movies;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihfazh.moviecatalog.databinding.FragmentMovieListBinding;
import com.ihfazh.moviecatalog.ui.home.HomeViewModel;
import com.ihfazh.moviecatalog.ui.home.OnListItemClicked;
import com.ihfazh.moviecatalog.ui.movie.DetailMovieActivity;
import com.ihfazh.moviecatalog.ui.viewmodels.ViewModelFactory;
import com.ihfazh.moviecatalog.utils.dagger.ApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.DaggerApplicationComponent;

import javax.inject.Inject;

public class MovieListFragment extends Fragment implements OnListItemClicked {
    private static final String TAG = "MovieListFragment";
    private FragmentMovieListBinding binding;

    @Inject public ViewModelFactory factory;
    ApplicationComponent applicationComponent;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance() {
        return new MovieListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        applicationComponent = DaggerApplicationComponent.create();
        applicationComponent.inject(this);

        HomeViewModel viewModel = new ViewModelProvider(requireActivity(), factory).get(HomeViewModel.class);

        binding.rvMovies.setHasFixedSize(true);
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));

        MovieListAdapter adapter = new MovieListAdapter();
        adapter.setListener(this);
        viewModel.loadMovies().observe(getViewLifecycleOwner(), adapter::setMovieEntities);

        binding.rvMovies.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(String title) {
        Intent intent = new Intent(requireActivity(), DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.MOVIE_TITLE, title);
        startActivity(intent);
    }
}