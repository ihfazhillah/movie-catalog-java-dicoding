package com.ihfazh.moviecatalog.ui.home;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;

import com.ihfazh.moviecatalog.R;
import com.ihfazh.moviecatalog.data.entities.MovieEntity;
import com.ihfazh.moviecatalog.data.entities.TvShowEntity;
import com.ihfazh.moviecatalog.utils.DummyData;
import com.ihfazh.moviecatalog.utils.EspressoIdlingResources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/*
Home:
- Memiliki dua tab
- tab default menampilkan 10 data movie
- ketika tab tv movies di klik, maka akan tampilkan data tv movies 10 data

Detail Movie:
- klik data movie pertama
- memastikan data tampil (data apa saja?)

Detail Tv Show:
- klik data tv show pertama
- memastikan data tampil (data apa saja ?)
 */


public class HomeActivityTest {


    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(HomeActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getEspressoIdlingResource());
    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getEspressoIdlingResource());
    }

    @Test
    public void testHomeHasTwoTab(){
        // Tab Movies and TV Shows
        onView(withText("Movies")).check(matches(isDisplayed()));
        onView(withText("TV Shows")).check(matches(isDisplayed()));
    }

    @Test
    public void testMoviesList(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
    }

    @Test
    public void testTVShowsList(){
        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()));
    }

    @Test
    public void testMovieDetailView(){
        /*
        Detail Movie:
        - klik data movie pertama
                - memastikan data tampil (data apa saja?)
         */
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        /*
        - title
        - poster image
        - tanggal rilis
        - status
        - overview
         */


        onView(withId(R.id.title)).check(matches(isDisplayed()));

        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()));

        onView(withId(R.id.duration)).check(matches(isDisplayed()));
        onView(withId(R.id.status)).check(matches(isDisplayed()));
        onView(withId(R.id.overview)).check(matches(isDisplayed()));
    }

    @Test
    public void testTVShow(){
        /*
        Detail tvshow:
        - klik data tvshow pertama
            - memastikan data tampil (data apa saja?)
         */
        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        /*
        - title
        - poster image
        - status
        - overview
        - type
        - score
         */


        onView(withId(R.id.title)).check(matches(isDisplayed()));

        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()));

        onView(withId(R.id.status)).check(matches(isDisplayed()));

        onView(withId(R.id.overview)).check(matches(isDisplayed()));

        onView(withId(R.id.type)).check(matches(isDisplayed()));
        onView(withId(R.id.score)).check(matches(isDisplayed()));
    }
}