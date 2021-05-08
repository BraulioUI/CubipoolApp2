package pe.edu.upc.myapplication.ui.user
import android.util.Log
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.lifecycle.ViewModelProvider
import pe.edu.upc.myapplication.databinding.FragmentRegisterBinding
import pe.edu.upc.myapplication.viewmodel.user.UserViewModel
import pe.edu.upc.myapplication.viewmodel.user.UserViewModelFactory

class RegisterFragment : Fragment() {

    private var _binding:FragmentRegisterBinding? = null
    private lateinit var viewModel: UserViewModel
    private var message: String = "no funciona"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(layoutInflater)

        _binding = binding
        val factory = UserViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        viewModel.isCorrect.observe(viewLifecycleOwner,{correct->
            Log.i("A: ",correct.toString())
            val register = RegisterFragmentDirections.navigateToRegisterSucessFragment()
            NavHostFragment.findNavController(this)
                .navigate(register)
        })
        viewModel.message.observe(viewLifecycleOwner,{msg ->
            Log.i("B: ",msg.toString())
            this.message = msg
            validateTransition(message)
            Log.i("FRAGMENTREGISTER: ",message)
        })


        binding.registerButton.setOnClickListener {
            Log.i("ETCODE",binding.codeField.text.toString())
            viewModel.regis(
                binding.codeField.text.toString(),
                binding.nameField.text.toString(),
                binding.lastnameField.text.toString(),
                binding.passwordField.text.toString(),
            )



        }

        return binding.root
    }
    private fun validateTransition(message:String) {
        Toast.makeText(this.context,message, Toast.LENGTH_LONG).show()
    }

}