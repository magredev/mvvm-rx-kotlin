package com.magre.challenge.ui

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import com.magre.challenge.R
import com.magre.challenge.ui.common.BaseActivity
import com.magre.challenge.ui.movielist.MovieListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), SearchView.OnQueryTextListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpToolbar()

        loadMovieListFragment()
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.search).getActionView() as SearchView
        searchView.setQueryHint(getString(R.string.search_movie))
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (currentFragment is MovieListFragment) {
            (currentFragment as MovieListFragment).searchMovie(newText ?: "")
        }

        return true
    }
}
