package pe.edu.upc.myapplication.ui.user
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import pe.edu.upc.myapplication.databinding.FragmentRegisterSuccessBinding


class RegisterFragmentSuccess : Fragment() {

    private var _binding:FragmentRegisterSuccessBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterSuccessBinding.inflate(layoutInflater)
        binding.loginButton.setOnClickListener{
            val action = RegisterFragmentSuccessDirections.navigateToLoginFragment()
            NavHostFragment.findNavController(this)
                .navigate(action)
        }
        _binding = binding

        return binding.root
    }


}