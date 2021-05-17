package pe.edu.upc.myapplication.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.upc.myapplication.databinding.FragmentSearchCubicleBinding

class SearchCubicleFragment: Fragment() {

    private var _binding: FragmentSearchCubicleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchCubicleBinding.inflate(inflater,container,false)


        return binding.root
    }
}