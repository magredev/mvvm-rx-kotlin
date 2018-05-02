package com.magre.challenge.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.magre.challenge.PrivaliaApp
import com.magre.challenge.repository.api.GenericMovieResponseMapper
import com.magre.challenge.viewmodel.data.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel() : ViewModel() {

    companion object{
        private var INSTANCE : MovieViewModel? = null

        private val movieRepository = PrivaliaApp.injectMovieRepository()

        fun getInstance(activity: FragmentActivity): MovieViewModel? {
            if (INSTANCE == null) {
                INSTANCE = ViewModelProviders.of(activity).get(MovieViewModel::class.java)
            }
            return INSTANCE
        }
    }

    private var isDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    private var movies: MutableLiveData<List<Movie>> = MutableLiveData()

    private var totalPages: Int = 0
    private var currentPage: Int = 1

    private var query: String = ""

    fun resetCurrentPage() {
        currentPage = 1
    }

    fun setQuery(query: String) {
        this.query = query
    }

    fun isDataLoading() : LiveData<Boolean> = isDataLoading

    fun getMovies() : LiveData<List<Movie>> = movies

    fun loadMovies() {
        isDataLoading.postValue(true)

        movieRepository.getMovies(query, currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    totalPages = result.totalPages
                    if (currentPage < totalPages) {
                        currentPage += 1
                        movies.postValue(GenericMovieResponseMapper().toMovieList(result))
                    }
                    isDataLoading.postValue(false)
                },
                        { error ->
                            error.printStackTrace()
                            isDataLoading.postValue(false)
                        }
                )
    }
}
