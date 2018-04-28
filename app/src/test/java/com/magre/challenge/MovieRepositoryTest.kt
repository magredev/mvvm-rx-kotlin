package com.magre.challenge

import com.magre.challenge.repository.MovieRepository
import com.magre.challenge.repository.api.GenericMovieResponse
import com.magre.challenge.repository.api.MovieResponse
import com.magre.challenge.repository.api.TMDBService
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class MovieRepositoryTest {

    lateinit var movieRepository: MovieRepository
    lateinit var api: TMDBService

    @Before
    fun setup() {
        api = mock()
        movieRepository = MovieRepository(api)
    }

    @Test
    fun testGetPopularMovies() {
        `when`(api.getPopularMovies(1)).thenReturn(Observable.just(popularMoviesResponseMock()))

        movieRepository.getPopularMovies(1).test()
                .assertValueAt(0, { it != null })
                .assertValueAt(0, { it.results.isNotEmpty() })
    }

    @Test
    fun testSearchMovies() {
        `when`(api.searchMovie("The", 1)).thenReturn(Observable.just(popularMoviesResponseMock()))

        movieRepository.searchMovies("The", 1).test()
                .assertValueAt(0, { it != null })
                .assertValueAt(0, { it.results.isNotEmpty() })
    }

    fun popularMoviesResponseMock() = GenericMovieResponse(1, 19899, 995,
            listOf(MovieResponse(1509, 337167, false, 6f,
                    "Fifty Shades Freed", 523.855789f,
                    "/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg", "en",
                    "Fifty Shades Freed", listOf(18, 10749),
                    "/9ywA15OAiwjSTvg3cBs9B7kOCBF.jpg", false,
                    "Believing they have left behind shadowy figures from their past, " +
                            "newlyweds Christian and Ana fully embrace an inextricable connection and " +
                            "shared life of luxury. But just as she steps into her role as Mrs. Grey " +
                            "and he relaxes into an unfamiliar stability, new threats could jeopardize " +
                            "their happy ending before it even begins.", "2018-02-07")))
}
