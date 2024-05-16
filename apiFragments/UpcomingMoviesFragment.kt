package com.ehsieh2.letswatchtv.apiFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehsieh2.letswatchtv.MoviesAdapter
import com.ehsieh2.letswatchtv.MoviesViewModel
import com.ehsieh2.letswatchtv.databinding.FragmentUpcomingMoviesBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class UpcomingMoviesFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingMoviesBinding
    private val viewModel: MoviesViewModel by activityViewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUpcomingMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        viewModel.fetchUpcomingMovies()
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter { movie ->
            // Define the onClick action
            Toast.makeText(context, "Clicked: ${movie.title}", Toast.LENGTH_LONG).show()
            // Navigate to the movie details fragment and pass movie id as argument
            val action = UpcomingMoviesFragmentDirection.actionUpcomingMoviesFragmentToMovieDetailsFragment(movie.id)
            findNavController().navigate(action)
        }

        binding.recyclerViewUpcoming.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.upcomingMovies.observe(viewLifecycleOwner) { movies ->
            (binding.recyclerViewUpcoming.adapter as MoviesAdapter).submitList(movies)
            binding.progressBar.visibility = View.GONE
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }
}
