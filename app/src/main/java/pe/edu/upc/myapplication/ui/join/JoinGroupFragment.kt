package pe.edu.upc.myapplication.ui.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import pe.edu.upc.myapplication.databinding.FragmentJoinGroupBinding

class JoinGroupFragment: Fragment() {

    private var _binding:FragmentJoinGroupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentJoinGroupBinding.inflate(inflater,container,false)


        binding.btnJoin.setOnClickListener {
            val action = JoinGroupFragmentDirections.actionJoinGroupFragment2ToJoinActivity()

            NavHostFragment.findNavController(this).navigate(action)
        }

        return binding.root
    }
}