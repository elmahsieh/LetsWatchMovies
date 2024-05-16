package com.ehsieh2.letswatchtv

interface MovieDetailsCallback {
    fun onSuccess(movie: Movie)
    fun onError(error: String)
}
