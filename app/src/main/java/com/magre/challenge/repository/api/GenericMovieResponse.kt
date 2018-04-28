package com.magre.challenge.repository.api

import com.google.gson.annotations.SerializedName

data class GenericMovieResponse(

        @SerializedName("page")
        val page: Int,

        @SerializedName("total_results")
        val totalResults: Int,

        @SerializedName("total_pages")
        val totalPages: Int,

        @SerializedName("results")
        val results: List<MovieResponse>
)
