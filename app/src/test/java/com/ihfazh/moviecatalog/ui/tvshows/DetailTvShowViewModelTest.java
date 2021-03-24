package com.ihfazh.moviecatalog.ui.tvshows;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;
import com.ihfazh.moviecatalog.utils.DummyData;
import com.ihfazh.moviecatalog.utils.LiveDataUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DetailTvShowViewModelTest {
    DetailTvShowViewModel viewModel;
    List<TvShowEntity> tvShows = DummyData.generateTvShows();
    TvShowEntity firstTvShow = tvShows.get(0);

    @Mock public TMDBRepository repository;
    @Mock public Observer<TvShowEntity> observer;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        viewModel = new DetailTvShowViewModel(repository);
        viewModel.setId(firstTvShow.getId());
    }

    @Test
    public void getTvShowByTitle() {
        MutableLiveData<TvShowEntity> tvShow = new MutableLiveData<>();
        tvShow.setValue(firstTvShow);

        Mockito.when(repository.getTvById(firstTvShow.getId())).thenReturn(tvShow);

        viewModel.tvShow.observeForever(observer);
        verify(observer).onChanged(firstTvShow);
    }

    @Test
    public void setBookmark() {
        MutableLiveData<TvShowEntity> tvShow = new MutableLiveData<>();
        tvShow.setValue(firstTvShow);
        Mockito.when(repository.getTvById(firstTvShow.getId())).thenReturn(tvShow);
        LiveDataUtil.getValue(viewModel.tvShow);

        viewModel.setBookmark();
        verify(repository).setBookmark(eq(firstTvShow), anyBoolean());

    }
}