package com.magre.challenge.repository.api

import com.magre.challenge.BuildConfig
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun getPopularMovies(@Query("page") page: Int) : Observable<GenericMovieResponse>

    @GET("search/movie?api_key=${BuildConfig.API_KEY}")
    fun searchMovie(@Query("query") query: String, @Query("page") page: Int)
            : Observable<GenericMovieResponse>
}
