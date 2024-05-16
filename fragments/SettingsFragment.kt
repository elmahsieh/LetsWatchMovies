import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ehsieh2.letswatchtv.R
import com.ehsieh2.letswatchtv.SettingsViewModel
import com.ehsieh2.letswatchtv.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[SettingsViewModel::class.java]
        val sharedPrefs = requireActivity().getSharedPreferences("AppSettingsPrefs", Context.MODE_PRIVATE)

        // Observe the LiveData and update the switch whenever the LiveData changes
        viewModel.useApi.observe(viewLifecycleOwner) { useApi ->
            binding.apiToggleSwitch.isChecked = useApi
        }

        // Listen for changes in the switch's state
        binding.apiToggleSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveUseApiSetting(isChecked)
            // Optionally send an event or use LiveData to notify other parts of the app
        }

        binding.radioGroupAnimation.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioAnimationOn -> sharedPrefs.edit().putBoolean("AnimationEnabled", true).apply()
                R.id.radioAnimationOff -> sharedPrefs.edit().putBoolean("AnimationEnabled", false).apply()
            }
        }
        // Set the initial state
        val isAnimationEnabled = sharedPrefs.getBoolean("AnimationEnabled", true)
        binding.radioGroupAnimation.check(if (isAnimationEnabled) R.id.radioAnimationOn else R.id.radioAnimationOff)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
