import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ehsieh2.letswatchtv.R
import com.ehsieh2.letswatchtv.animationSettings.AppPreferences
import com.ehsieh2.letswatchtv.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Load and start the shake animation on the ImageView
        if (AppPreferences.isAnimationEnabled(requireContext())) {
            val shakeAnimation = AnimationUtils.loadAnimation(context, R.anim.shake_animation)
            binding.imageViewShake.startAnimation(shakeAnimation)
        }

        binding.exploreMoviesButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_moviesFragment)
        }
        binding.exploreSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
        binding.infoButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        }
        binding.notepadButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notepadFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
