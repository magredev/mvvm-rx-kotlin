package com.magre.challenge.repository.api

import com.magre.challenge.viewmodel.data.Movie

class GenericMovieResponseMapper {

    fun toMovieList(response: GenericMovieResponse) : List<Movie> {
        var movieList: List<Movie> = emptyList()
        for (item in response.results) {
            movieList = movieList.plus(MovieResponseMapper().toMovie(item))
        }
        return movieList
    }
}
