package com.ihfazh.moviecatalog.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.ihfazh.moviecatalog.R;
import com.ihfazh.moviecatalog.utils.EspressoIdlingResources;
import com.ihfazh.moviecatalog.utils.TMDBUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/*
Home:
- Memiliki dua tab
- tab default menampilkan list data movie
- ketika tab tv movies di klik, maka akan tampilkan data tv movies 10 data

Detail Movie:
- klik data movie pertama
- memastikan data tampil (data apa saja?)

Detail Tv Show:
- klik data tv show pertama
- memastikan data tampil (data apa saja ?)
 */


@RunWith(AndroidJUnit4ClassRunner.class)
public class HomeActivityTest {
    private static final String TAG = "HomeActivityTest";
    private final MockWebServer webServer = new MockWebServer();

    @Before
    public void setUp() throws Exception {
        setWebserverDispatcher();
        webServer.start(8080);

        TMDBUtils.memoryDb = true;
        TMDBUtils.BASE_URL = webServer.url("/").toString();

        ActivityScenario.launch(HomeActivity.class);
        IdlingRegistry.getInstance().register(EspressoIdlingResources.getEspressoIdlingResource());
    }

    @After
    public void tearDown() throws IOException {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.getEspressoIdlingResource());
        webServer.shutdown();
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
        onView(withId(R.id.title)).check(matches(withText("Fight Club")));

        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()));

        onView(withId(R.id.duration)).check(matches(isDisplayed()));
        onView(withId(R.id.duration)).check(matches(withText("139")));

        onView(withId(R.id.status)).check(matches(isDisplayed()));
        onView(withId(R.id.status)).check(matches(withText("Released")));

        onView(withId(R.id.overview)).check(matches(isDisplayed()));
        onView(withId(R.id.overview)).check(matches(withText("A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.")));

        // bahasa
        onView(withId(R.id.language)).check(matches(isDisplayed()));
        onView(withId(R.id.language)).check(matches(withText("en")));
        // budget
        onView(withId(R.id.budget)).check(matches(isDisplayed()));
        onView(withId(R.id.budget)).check(matches(withText("63000000")));
        // score
        onView(withId(R.id.score)).check(matches(isDisplayed()));
        onView(withId(R.id.score)).check(matches(withText("8.4")));

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
        onView(withId(R.id.title)).check(matches(withText("Thursday at One")));

        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()));


        onView(withId(R.id.overview)).check(matches(isDisplayed()));
        onView(withId(R.id.overview)).check(matches(withSubstring("Thursday at One was an Australian daytime")));

        // we need to scroll, the overview is much.
        onView(withId(R.id.score)).perform(scrollTo());

        onView(withId(R.id.type)).check(matches(isDisplayed()));
        onView(withId(R.id.type)).check(matches(withText("Scripted")));
        onView(withId(R.id.score)).check(matches(isDisplayed()));
        onView(withId(R.id.score)).check(matches(withText("0.0")));

        onView(withId(R.id.status)).check(matches(isDisplayed()));
        onView(withId(R.id.status)).check(matches(withText("Ended")));
    }

    @Test
    public void testCheckBookmarkIsEmpty(){
        onView(withId(R.id.action_favorite)).perform(click());
        onView(withText("Movies")).check(matches(isDisplayed()));
        onView(withText("TV Shows")).check(matches(isDisplayed()));

        onView(withId(R.id.rv_movies)).check(matches(hasChildCount(0)));

        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tv_shows)).check(matches(hasChildCount(0)));
    }

    @Test
    public void testAddMovieBookmark_check_thenRemove(){
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_bookmark)).perform(click());

        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.action_favorite)).perform(click());
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movies)).check(matches(hasChildCount(1)));

        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_bookmark)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withId(R.id.rv_movies)).check(matches(hasChildCount(0)));
    }


    @Test
    public void testAddTvBookmark_check_thenRemove(){
        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_bookmark)).perform(click());

        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.action_favorite)).perform(click());
        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_shows)).check(matches(hasChildCount(1)));

        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_bookmark)).perform(click());
        onView(withContentDescription("Navigate up")).perform(click());
        onView(withText("TV Shows")).perform(click());
        onView(withId(R.id.rv_tv_shows)).check(matches(hasChildCount(0)));
    }

    @NonNull
    private String getFileContent(String fileName) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder file = new StringBuilder();
        String line = reader.readLine();
        while (line != null){
            file.append(line);
            line = reader.readLine();
        }
        return file.toString();
    }
    private void setWebserverDispatcher() {
        webServer.setDispatcher(new Dispatcher() {
            @NonNull
            @Override
            public MockResponse dispatch(@NonNull RecordedRequest recordedRequest) throws InterruptedException {
                Log.d(TAG, "dispatch: " + recordedRequest.getPath());
                String path = recordedRequest.getPath();
                assert path != null;
                if (path.contains("tv/popular")){
                    Log.d(TAG, "TV Popular: " + recordedRequest.getPath());
                    try {
                        return new MockResponse()
                                .setResponseCode(200)
                                .setBody(getFileContent("popular_tv_200_response.json"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "error tv: ", e);
                    }
                } else if(path.contains("movie/popular")){
                    Log.d(TAG, "Movie Popular: " + recordedRequest.getPath());
                    try {
                        return new MockResponse()
                                .setResponseCode(200)
                                .setBody(getFileContent("popular_movies_200_response.json"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "error movie: ", e);
                    }
                } else if (path.contains("movie/")) {
                    Log.d(TAG, "detail movie: " + recordedRequest.getPath());
                    try {
                        return new MockResponse()
                                .setResponseCode(200)
                                .setBody(getFileContent("detail_movie_200_response.json"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "error movie: ", e);
                    }
                }
             else if (path.contains("tv/")) {
                    Log.d(TAG, "detail tv: " + recordedRequest.getPath());

                    try {
                        return new MockResponse()
                                .setResponseCode(200)
                                .setBody(getFileContent("detail_tv_200_response.json"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "error movie: ", e);
                    }
            }
                Log.d(TAG, "Not Found" + recordedRequest.getPath());
                return new MockResponse().setResponseCode(500);
            }
        });
    }
}