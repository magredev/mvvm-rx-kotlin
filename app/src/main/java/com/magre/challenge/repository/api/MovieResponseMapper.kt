package com.magre.challenge.repository.api

import com.magre.challenge.viewmodel.data.Movie

class MovieResponseMapper {

    fun toMovie(response: MovieResponse) : Movie {
        return Movie(response.title)
    }
}
