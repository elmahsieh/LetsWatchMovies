package com.ehsieh2.letswatchtv.dataHandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ehsieh2.letswatchtv.Movie
import com.ehsieh2.letswatchtv.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository {
    private val apiKey = "f30a516df866e8e39ffc019d1fcace2f"

    fun fetchUpcomingMovies(onSuccess: (List<Movie>) -> Unit, onFailure: (String) -> Unit) {
        RetrofitClient.instance.getUpcomingMovies(apiKey, 1).enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onFailure("Network error: ${t.message ?: "Unknown error"}")
            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let(onSuccess) ?: onFailure("No upcoming movies found")
                } else {
                    onFailure("Error fetching upcoming movies: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            }
        })
    }

    fun fetchTopRatedMovies(page: Int, onSuccess: (List<Movie>) -> Unit, onFailure: (String) -> Unit) {
        RetrofitClient.instance.getTopRatedMovies(apiKey, page).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let(onSuccess) ?: onFailure("No top rated movies found")
                } else {
                    onFailure("Error fetching top rated movies: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onFailure("Network error: ${t.message ?: "Unknown error"}")
            }
        })
    }

    fun fetchPopularMovies(onSuccess: (List<Movie>) -> Unit, onFailure: (String) -> Unit) {
        RetrofitClient.instance.getPopularMovies(apiKey, 1).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful) {
                    response.body()?.results?.let {
                        if (it.isNotEmpty()) {
                            onSuccess(it)
                        } else {
                            onFailure("No movies found.")
                        }
                    } ?: onFailure("No movies found.")
                } else {
                    onFailure("Error fetching movies: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                onFailure("Network error: ${t.message ?: "Unknown error"}")
            }
        })
    }

    fun getMovieDetails(movieId: Int): LiveData<Movie?> {
        val movieDetailsLiveData = MutableLiveData<Movie?>()
        val request = RetrofitClient.instance.getMovieDetails(movieId)
        request.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieDetailsLiveData.postValue(null)
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    movieDetailsLiveData.postValue(response.body())
                } else {
                    movieDetailsLiveData.postValue(null)
                }
            }
        })
        return movieDetailsLiveData
    }
}
