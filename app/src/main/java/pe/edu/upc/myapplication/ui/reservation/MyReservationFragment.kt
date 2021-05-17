package pe.edu.upc.myapplication.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.upc.myapplication.databinding.FragmentMyReservationBinding


class MyReservationFragment: Fragment(){

    private var _binding: FragmentMyReservationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyReservationBinding.inflate(inflater,container,false)



        return  binding.root

    }

}