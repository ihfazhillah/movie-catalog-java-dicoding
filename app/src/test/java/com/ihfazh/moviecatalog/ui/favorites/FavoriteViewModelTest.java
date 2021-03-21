package com.ihfazh.moviecatalog.ui.favorites;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.repositories.TMDBRepository;
import com.ihfazh.moviecatalog.utils.LiveDataUtil;
import com.ihfazh.moviecatalog.utils.sql.Sort;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteViewModelTest {

    private TMDBRepository repository = mock(TMDBRepository.class);
    private FavoriteViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private Observer<Sort> sortObserver;

    @Mock
    private Observer<PagedList<MovieEntity>> movieObserver;

    @Before
    public void setUp() throws Exception {
        viewModel = new FavoriteViewModel(repository);
        viewModel.setSort(Sort.RANDOM);
    }

    @Test
    public void testLoadMovies(){
        LiveDataUtil.getValue(viewModel.loadMovies);
        verify(repository).getBookmarkedMovie(any(SimpleSQLiteQuery.class));
    }

    @Test
    public void testLoadBookmarked(){
        LiveDataUtil.getValue(viewModel.loadTvShows);
        verify(repository).getBookmarkedTv(any(SimpleSQLiteQuery.class));
    }
}