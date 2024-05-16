package com.ehsieh2.letswatchtv.apiFragments

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ehsieh2.letswatchtv.R

class PopularMoviesFragmentDirections {
    companion object {
        fun actionPopularMoviesFragmentToMovieDetailsFragment(movieId: Int): NavDirections {
            return ActionPopularMoviesFragmentToMovieDetailsFragment(movieId)
        }

        private class ActionPopularMoviesFragmentToMovieDetailsFragment(private val movieId: Int) :
            NavDirections {
            override fun getArguments(): Bundle {
                val result = Bundle()
                result.putInt("movieId", movieId)
                return result
            }

            override fun getActionId(): Int {
                return R.id.action_popularMoviesFragment_to_movieDetailsFragment
            }
        }
    }
}
