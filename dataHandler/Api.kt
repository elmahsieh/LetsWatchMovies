package com.ehsieh2.letswatchtv.dataHandler

import com.ehsieh2.letswatchtv.Movie
import com.ehsieh2.letswatchtv.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Call<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: Int): Call<Movie>

}
