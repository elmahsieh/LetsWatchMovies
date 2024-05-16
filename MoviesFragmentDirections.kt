package com.ehsieh2.letswatchtv

import android.os.Bundle
import androidx.navigation.NavDirections

class MoviesFragmentDirections private constructor() {
    companion object {
        fun actionMoviesFragmentToMovieDetailsFragment(movieId: Int): NavDirections {
            return ActionMoviesFragmentToMovieDetailsFragment(movieId)
        }

        private class ActionMoviesFragmentToMovieDetailsFragment(private val movieId: Int) : NavDirections {
            override fun getArguments(): Bundle {
                val result = Bundle()
                result.putInt("movieId", movieId)
                return result
            }

            override fun getActionId(): Int {
                return R.id.action_moviesFragment_to_movieDetailsFragment
            }
        }
    }
}
