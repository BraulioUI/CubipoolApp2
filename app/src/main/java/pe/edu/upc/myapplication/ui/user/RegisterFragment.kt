package pe.edu.upc.myapplication.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import pe.edu.upc.myapplication.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding:FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(layoutInflater)

        _binding = binding
        binding.registerButton.setOnClickListener{

            val action = RegisterFragmentDirections.navigateToRegisterSucessFragment()

            NavHostFragment.findNavController(this)
                .navigate(action)
        }
        return binding.root
    }


}