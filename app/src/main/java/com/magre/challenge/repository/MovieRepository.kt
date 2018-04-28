package com.magre.challenge.repository

import com.magre.challenge.repository.api.GenericMovieResponse
import com.magre.challenge.repository.api.TMDBService
import io.reactivex.Observable

class MovieRepository(val api: TMDBService) {

    fun getPopularMovies(page: Int) : Observable<GenericMovieResponse> =
            api.getPopularMovies(page)

    fun searchMovies(query: String, page: Int) : Observable<GenericMovieResponse> =
            api.searchMovie(query, page)
}
