package com.ihfazh.moviecatalog.ui.home.tvshows;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihfazh.moviecatalog.databinding.FragmentTvShowsBinding;
import com.ihfazh.moviecatalog.ui.home.HomeViewModel;
import com.ihfazh.moviecatalog.ui.tvshows.DetailTvShowActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TvShowsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TvShowsFragment extends Fragment {
    private FragmentTvShowsBinding binding;

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

        binding.rvTvShows.setHasFixedSize(true);
        binding.rvTvShows.setLayoutManager(new LinearLayoutManager(getContext()));

        TvShowsAdapter adapter = new TvShowsAdapter();
        adapter.setListener(title -> {
            Intent intent = new Intent(requireActivity(), DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.TV_SHOW_TITLE, title);
            startActivity(intent);
        });

        HomeViewModel modelView = new ViewModelProvider(requireActivity(), new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        adapter.setTvShows(modelView.loadTvShows());

        binding.rvTvShows.setAdapter(adapter);
    }
}