package com.magre.challenge.ui.movielist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magre.challenge.R
import com.magre.challenge.extension.d
import com.magre.challenge.extension.visible
import com.magre.challenge.ui.movielist.adapter.MovieListAdapter
import com.magre.challenge.viewmodel.MovieViewModel
import com.magre.challenge.viewmodel.data.Movie
import kotlinx.android.synthetic.main.fragment_movie_list.*



class MovieListFragment : Fragment() {

    private var movieViewModel: MovieViewModel? = null

    private lateinit var movieAdapter: MovieListAdapter


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = MovieViewModel.getInstance(activity!!)

        initMovieList()

        initObservers()

        movieViewModel?.loadPopularMovies()
    }

    private fun initMovieList() {
        movieAdapter = MovieListAdapter().apply {
            onClickAction = {
                d("Movie clicked: " + it.title)
            }
        }

        val mLayoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false)

        movieList.apply {
            adapter = movieAdapter
            setHasFixedSize(true)
            layoutManager = mLayoutManager

            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (isEndOfListAndNotLoading(mLayoutManager)) {
                        movieViewModel?.loadPopularMovies()
                    }
                }
            })
        }
    }

    private fun initObservers() {
        movieViewModel?.isDataLoading()?.observe(this, Observer {
            it?.let {
                progressBar.visible(it)
            }
        })

        movieViewModel?.getPopularMovies()?.observe(this, Observer {
            it?.let {
                showPopularMovies(it)
            }
        })

        movieViewModel?.getSearchMovies()?.observe(this, Observer {
            it?.let {

            }
        })
    }

    private fun showPopularMovies(movies: List<Movie>) {
        movieAdapter.addMoreItems(movies)
    }

    private fun isEndOfListAndNotLoading(layoutManager: LinearLayoutManager) : Boolean {
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!movieViewModel!!.isDataLoading().value!! &&
                visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0) {
            return true
        }

        return false
    }
}
