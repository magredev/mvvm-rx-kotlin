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
    private var popularMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    private var searchMovies: MutableLiveData<List<Movie>> = MutableLiveData()

    private var totalPages: Int = 0
    private var currentPage: Int = 1

    fun isDataLoading() : LiveData<Boolean> = isDataLoading

    fun getPopularMovies() : LiveData<List<Movie>> = popularMovies

    fun getSearchMovies() : LiveData<List<Movie>> = searchMovies

    fun loadPopularMovies() {
        isDataLoading.postValue(true)
        movieRepository.getPopularMovies(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    totalPages = result.totalPages
                    if (currentPage < totalPages) {
                        currentPage += 1
                        popularMovies.postValue(GenericMovieResponseMapper().toMovieList(result))
                    }
                    isDataLoading.postValue(false)
                },
                        { error ->
                            error.printStackTrace()
                            isDataLoading.postValue(false)
                        }
                )
    }

    fun searchMovies(query: String) {
        isDataLoading.postValue(true)
        movieRepository.searchMovies(query, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    searchMovies.postValue(GenericMovieResponseMapper().toMovieList(result))
                    isDataLoading.postValue(false)
                },
                        { error ->
                            error.printStackTrace()
                            isDataLoading.postValue(false)
                        }
                )
    }
}
