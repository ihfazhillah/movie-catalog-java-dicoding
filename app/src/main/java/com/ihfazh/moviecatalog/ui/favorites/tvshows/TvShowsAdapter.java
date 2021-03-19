package com.ihfazh.moviecatalog.ui.favorites.tvshows;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.databinding.TvShowsItemBinding;
import com.ihfazh.moviecatalog.ui.favorites.OnListItemClicked;
import com.ihfazh.moviecatalog.utils.TMDBUtils;

public class TvShowsAdapter extends PagedListAdapter<TvShowEntity, TvShowsAdapter.ViewHolder> {
    private OnListItemClicked listener;

    protected TvShowsAdapter() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<TvShowEntity> diffCallback = new DiffUtil.ItemCallback<TvShowEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull TvShowEntity oldItem, @NonNull TvShowEntity newItem) {
            return oldItem.equals(newItem);
        }
    };


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
        TvShowEntity tvShow = getItem(position);
        if (tvShow != null){
            holder.bind(tvShow);
        }
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
                    .load(TMDBUtils.getFullImagePath(tvShow.getPoster_url()))
                    .into(this.binding.imgPoster);

            itemView.setOnClickListener(e -> {
                listener.onItemClicked(tvShow.getId());
            });
        }
    }
}
