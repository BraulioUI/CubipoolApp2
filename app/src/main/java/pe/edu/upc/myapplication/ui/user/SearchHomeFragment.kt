package pe.edu.upc.myapplication.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.upc.myapplication.databinding.FragmentSearchHomeBinding

class SearchHomeFragment : Fragment() {

    private var _binding: FragmentSearchHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSearchHomeBinding.inflate(layoutInflater)

        _binding = binding

        return binding.root
    }

}