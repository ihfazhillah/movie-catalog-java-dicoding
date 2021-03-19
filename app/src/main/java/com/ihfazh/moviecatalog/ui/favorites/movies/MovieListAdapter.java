package com.ihfazh.moviecatalog.ui.favorites.movies;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.databinding.MovieItemBinding;
import com.ihfazh.moviecatalog.utils.TMDBUtils;

public class MovieListAdapter extends PagedListAdapter<MovieEntity, MovieListAdapter.ViewHolder> {
    private static final String TAG = "MovieListAdapter";

    MovieListFragment listener;


    public MovieListAdapter() {
        super(diffCallback);
    }

    @Override
    public int getItemCount() {
        int size = super.getItemCount();
        Log.d(TAG, "getItemCount: " + size);
        return size;
    }

    static  DiffUtil.ItemCallback<MovieEntity> diffCallback = new DiffUtil.ItemCallback<MovieEntity>() {

        @Override
        public boolean areItemsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
            Log.d(TAG, "areItemsTheSame: " + oldItem.getTitle());
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull MovieEntity oldItem, @NonNull MovieEntity newItem) {
            return oldItem.equals(newItem);
        }
    };


    public void setListener(MovieListFragment listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieEntity movie = getItem(position);
        if (movie != null){
            holder.bind(movie);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final MovieItemBinding binding;
        public ViewHolder(@NonNull MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieEntity movie) {
            binding.title.setText(movie.getTitle());
            binding.hour.setText(movie.getLength());
            Glide.with(itemView)
                    .load(TMDBUtils.getFullImagePath(movie.getPosterUrl()))
                    .into(binding.imgPoster);
            itemView.setOnClickListener(e -> {
                listener.onItemClicked(movie.getId());
            });

        }
    }
}
