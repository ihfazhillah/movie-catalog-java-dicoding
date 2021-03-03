package com.ihfazh.moviecatalog.ui.tvshows;

import com.ihfazh.moviecatalog.data.TvShowEntity;
import com.ihfazh.moviecatalog.utils.DummyData;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DetailTvShowViewModelTest {
    DetailTvShowViewModel viewModel;
    List<TvShowEntity> tvShows = DummyData.generateTvShows();
    TvShowEntity firstTvShow = tvShows.get(0);


    @Before
    public void setUp() throws Exception {
        viewModel = new DetailTvShowViewModel();
    }

    @Test
    public void getTvShowByTitle() {
        TvShowEntity tvShowEntity = viewModel.getTvShowByTitle(firstTvShow.getTitle());
        assertNotNull(tvShowEntity);
        assertEquals(firstTvShow.getTitle(), tvShowEntity.getTitle());
    }
}