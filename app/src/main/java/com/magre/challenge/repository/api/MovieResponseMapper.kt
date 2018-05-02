package com.magre.challenge.repository.api

import com.magre.challenge.extension.getYear
import com.magre.challenge.viewmodel.data.Movie

class MovieResponseMapper {

    fun toMovie(response: MovieResponse) : Movie {
        return Movie(
                response.title ?: "",
                response.releaseDate?.getYear() ?: 0,
                response.overview ?: "",
                response.posterPath ?: "")
    }
}
