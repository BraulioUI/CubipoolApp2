package pe.edu.upc.myapplication.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import pe.edu.upc.myapplication.databinding.ActivityHomeBinding
import pe.edu.upc.myapplication.ui.user.MyReservationFragment


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val host:NavHostFragment = supportFragmentManager
            .findFragmentById(binding.fragmentHome.id) as NavHostFragment? ?: return

        val navController = host.navController

        NavigationUI.setupWithNavController(binding.activityMainBottomNavigationView,navController)

    }

}