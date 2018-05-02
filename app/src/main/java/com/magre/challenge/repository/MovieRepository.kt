package com.magre.challenge.repository

import com.magre.challenge.extension.d
import com.magre.challenge.repository.api.GenericMovieResponse
import com.magre.challenge.repository.api.TMDBService
import io.reactivex.Observable

class MovieRepository(val api: TMDBService) {

    fun getMovies(query: String, page: Int) : Observable<GenericMovieResponse> {
        if (query.isEmpty()) {
            d("Getting popular movies with page number $page")
            return api.getPopularMovies(page)
        } else {
            d("Getting movies with query $query and page number $page")
            return api.searchMovie(query, page)
        }
    }
}
