package com.lucasbrandt.movieselector

import com.lucasbrandt.movieselector.network.MovieService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivityViewModelTest {

    //This is the primary subject of tests, therefore named "subject"
    private val subject: MainActivityViewModel = MainActivityViewModel()
    private var mockWebServer = MockWebServer()

    @Before
    fun setup() {
        mockWebServer.start()
    }

    @After
    fun finish() {
        mockWebServer.shutdown()
    }

    @Test
    fun testNetworkCallSuccess() {
        val retrofit = buildRetrofit()

        mockWebServer.enqueue(
            MockResponse().setBody("{\"results\":[{\"popularity\":457.734,\"vote_average\":4850,\"video\":false,\"poster_path\":\"/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg\",\"id\":475557,\"adult\":false,\"backdrop_path\":\"/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg\",\"original_language\":\"en\",\"original_title\":\"Joker\",\"genre_ids\":[80,18,53],\"title\":\"Joker\",\"vote_average\":8.5,\"overview\":\"During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.\",\"release_date\":\"2019-10-04\"}],\"page\":1,\"total_results\":1387,\"dates\":{\"maximum\":\"2019-11-12\",\"minimum\":\"2019-09-25\"},\"total_pages\":70}")
                .setResponseCode(200)
        )

        val movieService = retrofit.create(MovieService::class.java)
        val call = movieService.getCurrentlyPlayingMovies("123")
        val callResult = call.execute()

        assertTrue(callResult.isSuccessful)
        assertEquals("Joker", callResult.body()?.results?.get(0)?.title)
    }

    @Test
    fun testNetworkCallFailure() {
        val retrofit = buildRetrofit()

        mockWebServer.enqueue(
            MockResponse().setBody("{\"status_code\":7,\"status_message\":\"Invalid API key: You must be granted a valid key.\",\"success\":false}")
                .setResponseCode(400)
        )

        val movieService = retrofit.create(MovieService::class.java)
        val call = movieService.getCurrentlyPlayingMovies("123")
        val callResult = call.execute()

        assertFalse(callResult.isSuccessful)
        assertEquals(null, callResult.body()?.results?.get(0)?.title)
    }

    @Test
    fun setMovieDetails_updatesObservableFields() {
        subject.setMovieDetails("title", "desc")

        assertEquals(subject.movieTitle.get(), "title")
        assertEquals(subject.movieDescription.get(), "desc")
    }

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}