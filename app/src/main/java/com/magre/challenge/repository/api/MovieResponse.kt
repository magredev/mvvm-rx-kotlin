package com.magre.challenge.repository.api

import com.google.gson.annotations.SerializedName

data class MovieResponse(

        @SerializedName("vote_count")
        val voteCount: Int,

        @SerializedName("id")
        val id: Int,

        @SerializedName("video")
        val isVideo: Boolean,

        @SerializedName("vote_average")
        val voteAverage: Float,

        @SerializedName("title")
        val title: String,

        @SerializedName("popularity")
        val popularity: Float,

        @SerializedName("poster_path")
        val posterPath: String,

        @SerializedName("original_language")
        val originalLanguage: String,

        @SerializedName("original_title")
        val originalTitle: String,

        @SerializedName("genre_ids")
        val genreIds: List<Int>,

        @SerializedName("backdrop_path")
        val backdropPath: String,

        @SerializedName("adult")
        val isAdult: Boolean,

        @SerializedName("overview")
        val overview: String,

        @SerializedName("release_date")
        val releaseDate: String
)
