package com.ihfazh.moviecatalog.ui.favorites.movies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ihfazh.moviecatalog.databinding.FragmentMovieListBinding;
import com.ihfazh.moviecatalog.ui.favorites.FavoriteViewModel;
import com.ihfazh.moviecatalog.ui.favorites.OnListItemClicked;
import com.ihfazh.moviecatalog.ui.movie.DetailMovieActivity;
import com.ihfazh.moviecatalog.ui.viewmodels.ViewModelFactory;
import com.ihfazh.moviecatalog.utils.dagger.ApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.DaggerApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApplicationModule;

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

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(requireContext()))
            .build();
        applicationComponent.inject(this);

        FavoriteViewModel viewModel = new ViewModelProvider(requireActivity(), factory).get(FavoriteViewModel.class);

        // harus dihilangkan. Kenapa? Karena size udah gak fixed kan? Bisa bertambah terus.
//        binding.rvMovies.setHasFixedSize(true);

        binding.rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));

        MovieListAdapter adapter = new MovieListAdapter();
        binding.rvMovies.setAdapter(adapter);
        adapter.setListener(this);
        viewModel.loadMovies().observe(getViewLifecycleOwner(), movieEntities -> {
            adapter.submitList(movieEntities);
            Log.d(TAG, "onChanged: " + movieEntities.toString());
        });

    }


    @Override
    public void onItemClicked(String title) {
        Intent intent = new Intent(requireActivity(), DetailMovieActivity.class);
        intent.putExtra(DetailMovieActivity.MOVIE_ID, title);
        intent.putExtra(DetailMovieActivity.FROM_FAVORITE_PAGE, true);
        startActivity(intent);
    }
}