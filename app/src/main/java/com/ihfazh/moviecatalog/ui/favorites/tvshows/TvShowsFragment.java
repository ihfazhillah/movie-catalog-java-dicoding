package com.ihfazh.moviecatalog.ui.favorites.tvshows;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ihfazh.moviecatalog.databinding.FragmentTvShowsBinding;
import com.ihfazh.moviecatalog.ui.favorites.FavoriteViewModel;
import com.ihfazh.moviecatalog.ui.tvshows.DetailTvShowActivity;
import com.ihfazh.moviecatalog.ui.viewmodels.ViewModelFactory;
import com.ihfazh.moviecatalog.utils.dagger.ApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.DaggerApplicationComponent;
import com.ihfazh.moviecatalog.utils.dagger.modules.ApplicationModule;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TvShowsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TvShowsFragment extends Fragment {
    private FragmentTvShowsBinding binding;

    @Inject public ViewModelFactory factory;
    ApplicationComponent component;

    public TvShowsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TvShowsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TvShowsFragment newInstance() {
        TvShowsFragment fragment = new TvShowsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTvShowsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(requireContext()))
                .build();
        component.inject(this);

        binding.rvTvShows.setLayoutManager(new LinearLayoutManager(getContext()));

       TvShowsAdapter adapter = new TvShowsAdapter();
        adapter.setListener(title -> {
            Intent intent = new Intent(requireActivity(), DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.TV_SHOW_TITLE, title);
            intent.putExtra(DetailTvShowActivity.FROM_FAVORITE_PAGE, true);
            startActivity(intent);
        });


        FavoriteViewModel modelView = new ViewModelProvider(requireActivity(), factory).get(FavoriteViewModel.class);
        modelView.loadTvShows().observe(getViewLifecycleOwner(), adapter::submitList);

        binding.rvTvShows.setAdapter(adapter);
    }
}