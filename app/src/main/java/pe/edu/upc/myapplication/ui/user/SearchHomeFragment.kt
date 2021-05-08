package pe.edu.upc.myapplication.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import pe.edu.upc.myapplication.databinding.FragmentSearchHomeBinding

class SearchHomeFragment : Fragment() {

    private var _binding: FragmentSearchHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchHomeBinding.inflate(layoutInflater)
        binding.searchButton.setOnClickListener{
            val action = RegisterFragmentSuccessDirections.navigateToLoginFragment()
            NavHostFragment.findNavController(this)
                .navigate(action)
        }
        _binding = binding

        return binding.root
    }

}