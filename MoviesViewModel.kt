import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ehsieh2.letswatchtv.dataHandler.AssetJsonReader
import com.ehsieh2.letswatchtv.dataHandler.MoviesRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application) {
    private val moviesRepository = MoviesRepository()

    private val _moviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> = _moviesList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    private val _topRatedMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>> = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<List<Movie>>()
    val upcomingMovies: LiveData<List<Movie>> = _upcomingMovies

    private val sharedPreferences = application.getSharedPreferences("favorite_movies", Context.MODE_PRIVATE)

    // Fetching movies from JSON for the movie list
    fun fetchMoviesFromJson() {
        try {
            val context = getApplication<Application>().applicationContext
            val movies = AssetJsonReader.getMoviesFromJson(context, "movies.json").results
            _moviesList.postValue(movies)
        } catch (e: Exception) {
            _errorMessage.postValue("Error loading movies from JSON: ${e.message}")
        }
    }

    //API 1 - Fetch Popular Movies
    fun fetchTopRatedMovies() {
        moviesRepository.fetchTopRatedMovies(1,  // Example: Fetch page 1
            onSuccess = { movies ->
                _topRatedMovies.postValue(movies)
            },
            onFailure = { error ->
                _errorMessage.postValue(error)
            }
        )
    }

    //API 2 - Fetch Upcoming Movies
    fun fetchUpcomingMovies() {
        moviesRepository.fetchUpcomingMovies(
            onSuccess = { movies ->
                _upcomingMovies.postValue(movies)
            },
            onFailure = { error ->
                _errorMessage.postValue(error)
            }
        )
    }

    fun fetchMovieDetails(movieId: Int): LiveData<Movie?> {
        return moviesRepository.getMovieDetails(movieId)
    }

    fun isMovieHearted(movieId: Int): Boolean {
        return sharedPreferences.getBoolean(movieId.toString(), false)
    }

    fun setMovieHearted(movieId: Int, hearted: Boolean) {
        sharedPreferences.edit().putBoolean(movieId.toString(), hearted).apply()
    }

}