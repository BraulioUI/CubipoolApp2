package pe.edu.upc.myapplication.ui.user

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.convertTo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import pe.edu.upc.myapplication.R
import pe.edu.upc.myapplication.databinding.ActivityMainBinding
import pe.edu.upc.myapplication.databinding.FragmentLoginBinding
import pe.edu.upc.myapplication.viewmodel.auth.AuthViewModel
import pe.edu.upc.myapplication.viewmodel.auth.AuthViewModelFactory

class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding? =null
    private lateinit var viewModel: AuthViewModel
    private var message: String = "no funciona"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentLoginBinding.inflate(layoutInflater)

        _binding = binding

        val factory = AuthViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        binding.btRegister.setOnClickListener {
            val action = LoginFragmentDirections.navigateToRegisterFragment()

            NavHostFragment.findNavController(this)
                .navigate(action)
        }

        viewModel.isCorrect.observe(viewLifecycleOwner,{correct ->
           Log.i("HEY: ",correct.toString())

            val action = LoginFragmentDirections.navigateTohome()
            NavHostFragment.findNavController(this)
                .navigate(action)
        })

        viewModel.message.observe(viewLifecycleOwner,{msg ->
            Log.i("HEY2: ",msg.toString())
            this.message = msg
            validateTransition(message)
            Log.i("FRAGMENTLOGIN: ",message)
        })


        binding.btLogin.setOnClickListener {
            Log.i("ETCODE",binding.etCode.text.toString())
            viewModel.auth(
                binding.etCode.text.toString(),
                binding.etPassword.text.toString()
            )



        }

        return binding.root
    }

    private fun validateTransition(message:String) {
        Toast.makeText(this.context,message,Toast.LENGTH_LONG).show()
    }




}