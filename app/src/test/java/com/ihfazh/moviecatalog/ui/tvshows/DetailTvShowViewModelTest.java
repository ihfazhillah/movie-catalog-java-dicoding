package com.ihfazh.moviecatalog.ui.tvshows;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;
import com.ihfazh.moviecatalog.utils.DummyData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    }

    @Test
    public void getTvShowByTitle() {
        MutableLiveData<TvShowEntity> tvShow = new MutableLiveData<>();
        tvShow.setValue(firstTvShow);

        Mockito.when(repository.getTvById(firstTvShow.getId())).thenReturn(tvShow);

        TvShowEntity tvShowEntity = viewModel.getTvShowById(firstTvShow.getId()).getValue();
        assertNotNull(tvShowEntity);
        assertEquals(firstTvShow.getTitle(), tvShowEntity.getTitle());

        viewModel.getTvShowById(firstTvShow.getId()).observeForever(observer);
        Mockito.verify(observer).onChanged(firstTvShow);
    }
}