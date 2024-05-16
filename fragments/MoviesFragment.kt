import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehsieh2.letswatchtv.MoviesAdapter
import com.ehsieh2.letswatchtv.MoviesFragmentDirections
import com.ehsieh2.letswatchtv.MoviesViewModel
import com.ehsieh2.letswatchtv.R
import com.ehsieh2.letswatchtv.SettingsViewModel
import com.ehsieh2.letswatchtv.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupRecyclerView() {
        // Properly initialize moviesAdapter with an onClick function
        moviesAdapter = MoviesAdapter { movie ->
            // Define the onClick action
            Toast.makeText(context, "Clicked: ${movie.title}", Toast.LENGTH_LONG).show()

            // Navigate to the movie details fragment and pass movie id as argument
            val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(movie.id)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settingsViewModel = ViewModelProvider(requireActivity())[SettingsViewModel::class.java]

        settingsViewModel.useApi.observe(viewLifecycleOwner) { useApi ->
            binding.btnTopRatedMovies.isEnabled = useApi
            binding.btnUpcomingMovies.isEnabled = useApi
        }

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        setupRecyclerView()

        viewModel.moviesList.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.submitList(movies)
        }

        binding.btnTopRatedMovies.setOnClickListener {
            if (settingsViewModel.useApi.value == true) {
                findNavController().navigate(R.id.action_movieListFragment_to_popularMoviesFragment)
                viewModel.fetchTopRatedMovies()
            } else {
                Toast.makeText(context, "API access is disabled", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnUpcomingMovies.setOnClickListener {
            if (settingsViewModel.useApi.value == true) {
                findNavController().navigate(R.id.action_movieListFragment_to_upcomingMoviesFragment)
                viewModel.fetchUpcomingMovies()
            } else {
                Toast.makeText(context, "API access is disabled", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }

        // Fetch movies from JSON when this fragment is created
        viewModel.fetchMoviesFromJson()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // To avoid memory leaks
    }
}
