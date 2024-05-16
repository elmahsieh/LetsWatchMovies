package com.ehsieh2.letswatchtv

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("backdrop_path") val backdropPath: String?,
    val id: Int,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("original_name") val originalName: String?,
    val overview: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("media_type") val mediaType: String?,
    val adult: Boolean,
    val title: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val popularity: Double,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    val name: String?,
    @SerializedName("origin_country") val originCountry: List<String>?
)
