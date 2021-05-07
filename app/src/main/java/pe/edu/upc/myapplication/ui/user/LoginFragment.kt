package pe.edu.upc.myapplication.ui.user

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    private lateinit var message: String

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
        binding.btLogin.setOnClickListener {
            viewModel.onClick(
                binding.etCode.text.toString(),
                binding.etPassword.text.toString()
            )
            //message = viewModel.getmessage()
            //validateTransition(message)
            val action = LoginFragmentDirections.navigateToHomeActivity()

            NavHostFragment.findNavController(this)
                .navigate(action)
        }

        return binding.root
    }

    private fun validateTransition(message:String) {
        when(message){
            "Ingreso correctamente" ->{
                Toast.makeText(this.context,message,Toast.LENGTH_LONG).show()
            }
            "Credenciales inválidas" ->{
                Toast.makeText(this.context,message,Toast.LENGTH_LONG).show()
            }
            "Algo salio mal"->{
                Toast.makeText(this.context,message,Toast.LENGTH_LONG).show()
            }
        }
    }




}