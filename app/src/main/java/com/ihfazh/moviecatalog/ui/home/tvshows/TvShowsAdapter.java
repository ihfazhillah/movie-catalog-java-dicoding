package com.ihfazh.moviecatalog.ui.home.tvshows;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.databinding.TvShowsItemBinding;
import com.ihfazh.moviecatalog.ui.home.OnListItemClicked;

import java.util.List;

public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.ViewHolder> {
    private List<TvShowEntity> tvShows;
    private OnListItemClicked listener;

    public void setTvShows(List<TvShowEntity> tvShows) {
        this.tvShows = tvShows;
    }

    public void setListener(OnListItemClicked listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TvShowsItemBinding binding = TvShowsItemBinding.inflate(
                LayoutInflater.from(parent.getContext())
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvShowEntity tvShow = tvShows.get(position);
        holder.bind(tvShow);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TvShowsItemBinding binding;

        public ViewHolder(@NonNull TvShowsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(TvShowEntity tvShow){
            this.binding.title.setText(tvShow.getTitle());
            Glide.with(this.binding.getRoot())
                    .load(tvShow.getPoster_url())
                    .into(this.binding.imgPoster);

            itemView.setOnClickListener(e -> {
                listener.onItemClicked(tvShow.getTitle());
            });
        }
    }
}
