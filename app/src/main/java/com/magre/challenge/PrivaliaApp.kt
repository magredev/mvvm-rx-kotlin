package com.magre.challenge

import android.app.Application
import com.magre.challenge.repository.MovieRepository
import com.magre.challenge.repository.api.TMDBService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PrivaliaApp : Application() {

    // todo: improve this using Dagger2!
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var api: TMDBService
        private lateinit var movieRepository: MovieRepository

        fun injectApi() = api

        fun injectMovieRepository() = movieRepository
    }

    override fun onCreate() {
        super.onCreate()

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()

        api = retrofit.create(TMDBService::class.java)

        movieRepository = MovieRepository(api)
    }
}
