package pe.edu.upc.myapplication.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.edu.upc.myapplication.databinding.FragmentMyReservationBinding


class MyReservationFragment: Fragment(){

    private var _binding: FragmentMyReservationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMyReservationBinding.inflate(layoutInflater)

        _binding = binding

        return  binding.root

    }

}