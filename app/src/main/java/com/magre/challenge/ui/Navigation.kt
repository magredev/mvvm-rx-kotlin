package com.magre.challenge.ui

import com.magre.challenge.R
import com.magre.challenge.ui.movielist.MovieListFragment

fun MainActivity.loadMovieListFragment() {
    supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MovieListFragment())
            .commit()
}
