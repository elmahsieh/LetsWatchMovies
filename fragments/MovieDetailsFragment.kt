import android.widget.ImageView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ehsieh2.letswatchtv.Movie
import com.ehsieh2.letswatchtv.MoviesViewModel
import com.ehsieh2.letswatchtv.animationSettings.AppPreferences
import com.ehsieh2.letswatchtv.databinding.FragmentMovieDetailsBinding
import com.ehsieh2.letswatchtv.showRateAppDialog

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        arguments?.let { bundle ->
            val movieId = bundle.getInt("movieId")
            viewModel.fetchMovieDetails(movieId).observe(viewLifecycleOwner) { movie ->
                if (movie != null) {
                    updateUI(movie)
                } else {
                    handleNoMovieDetails()
                }
            }
        }
        arguments?.getInt("movieId")?.let { movieId ->
            setupHeartButton(movieId)
        }
        binding.btnRateApp.setOnClickListener {
            showRateAppDialog(requireContext())
        }
    }

    private fun updateUI(movie: Movie) {
        binding.detailTitleTextView.text = movie.title
        binding.detailReleaseDateTextView.text = "Release Date: ${movie.releaseDate}"
        binding.detailLanguageTextView.text = "Language: ${movie.originalLanguage}"
        binding.detailVoteAverageTextView.text = "Rating: ${movie.voteAverage}/10"
        binding.detailOverviewTextView.text = movie.overview

        // Assuming you have a method to load images:
        loadImage(binding.detailPosterImageView, movie.backdropPath)
    }

    private fun loadImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            val requestManager = Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500$it")

            if (AppPreferences.isAnimationEnabled(requireContext())) {
                requestManager.transition(DrawableTransitionOptions.withCrossFade())
            }

            requestManager.into(imageView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleNoMovieDetails() {
        Toast.makeText(context, "Movie details are not available.", Toast.LENGTH_LONG).show()
    }

    private fun setupHeartButton(movieId: Int) {
        binding.heartToggleButton.isChecked = viewModel.isMovieHearted(movieId)

        binding.heartToggleButton.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setMovieHearted(movieId, isChecked)
            if (isChecked) {
                Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
