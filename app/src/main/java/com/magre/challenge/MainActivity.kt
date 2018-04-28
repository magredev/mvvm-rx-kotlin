package com.magre.challenge

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.magre.challenge.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private var movieViewModel: MovieViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel = MovieViewModel.getInstance(this)

        initObservers()

        movieViewModel?.loadPopularMovies()
    }

    private fun initObservers() {
        movieViewModel?.isDataLoading()?.observe(this, Observer {
            it?.let {

            }
        })

        movieViewModel?.getPopularMovies()?.observe(this, Observer {
            it?.let {
                showPopularMovies()
            }
        })

        movieViewModel?.getSearchMovies()?.observe(this, Observer {
            it?.let {

            }
        })
    }

    private fun showPopularMovies() {

    }
}
