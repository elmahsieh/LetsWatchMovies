import android.os.Bundle
import androidx.navigation.NavDirections
import com.ehsieh2.letswatchtv.R

class UpcomingMoviesFragmentDirection {
    companion object {
        fun actionUpcomingMoviesFragmentToMovieDetailsFragment(movieId: Int): NavDirections {
            return ActionUpcomingMoviesFragmentToMovieDetailsFragment(movieId)
        }

        private class ActionUpcomingMoviesFragmentToMovieDetailsFragment(private val movieId: Int) :
            NavDirections {
            override fun getArguments(): Bundle {
                val result = Bundle()
                result.putInt("movieId", movieId)
                return result
            }

            override fun getActionId(): Int {
                return R.id.action_upcomingMoviesFragment_to_movieDetailsFragment
            }
        }
    }
}
