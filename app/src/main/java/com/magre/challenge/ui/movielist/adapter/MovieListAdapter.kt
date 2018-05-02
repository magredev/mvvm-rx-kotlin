package com.magre.challenge.ui.movielist.adapter

import android.view.ViewGroup
import com.magre.challenge.ui.common.RecyclerViewAdapterBase
import com.magre.challenge.ui.common.ViewWrapper
import com.magre.challenge.viewmodel.data.Movie

class MovieListAdapter : RecyclerViewAdapterBase<Movie, MovieItemView>() {

    var onClickAction: ((movie: Movie) -> Unit)? = null

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): MovieItemView =
            MovieItemView(parent.context)

    override fun onBindViewHolder(holder: ViewWrapper<MovieItemView>, position: Int) {
        val movie = items[position]

        holder.view.apply {
            bind(movie)
        }

        holder.view.setOnClickListener {
            onClickAction?.invoke(items[position])
        }
    }
}
