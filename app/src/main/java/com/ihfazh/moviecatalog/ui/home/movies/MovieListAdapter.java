package com.ihfazh.moviecatalog.ui.home.movies;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.data.MovieEntity;
import com.ihfazh.moviecatalog.databinding.MovieItemBinding;
import com.ihfazh.moviecatalog.ui.home.OnListItemClicked;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    OnListItemClicked listener;

    private List<MovieEntity> movieEntities;

    public void setMovieEntities(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
    }

    public void setListener(OnListItemClicked listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = MovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext())
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieEntity movie = movieEntities.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieEntities.size();
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
                    .load(movie.getPosterUrl())
                    // todo: add on error
                    .into(binding.imgPoster);
            itemView.setOnClickListener(e -> {
                listener.onItemClicked(movie.getTitle());
            });

        }
    }
}
