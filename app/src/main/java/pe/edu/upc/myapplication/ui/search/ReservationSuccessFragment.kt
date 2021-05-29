package pe.edu.upc.myapplication.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import pe.edu.upc.myapplication.databinding.FragmentReservationSucessBinding

class ReservationSuccessFragment: Fragment() {

    private var _binding:FragmentReservationSucessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentReservationSucessBinding.inflate(inflater,container,false)

        binding.btnGotToHome.setOnClickListener {
            val action = ReservationSuccessFragmentDirections.actionReservationSuccessFragmentToHomeActivity2()
            NavHostFragment.findNavController(this).navigate(action)
        }

        return binding.root
    }
}