import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ehsieh2.letswatchtv.BuildConfig
import com.ehsieh2.letswatchtv.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewAppName.text = "Application Name: ${BuildConfig.APPLICATION_NAME}"
        binding.textViewVersionControl.text = "Version Control: ${BuildConfig.VERSION_CONTROL}"
        binding.textViewBuildDate.text = "Build Date: ${BuildConfig.BUILD_DATE}"
        binding.textViewBuildLanguage.text = "Build Language: ${BuildConfig.BUILD_LANGUAGE}"
        binding.textViewVersionName.text = "Version Name: ${BuildConfig.VERSION_NAME}"
        binding.textViewVersionCode.text = "Version Code: ${BuildConfig.VERSION_CODE}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
