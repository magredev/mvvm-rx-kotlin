package com.magre.challenge.ui.movielist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.magre.challenge.R
import com.magre.challenge.viewmodel.data.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieItemView constructor(
        context: Context) : RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.item_movie, this, true)
    }

    fun bind(movie: Movie) {
//        moviePicture

        movieTitle.text = movie.title
        movieYear.text = movie.releaseYear.toString()
        movieOverview.text = movie.overview
    }
}
