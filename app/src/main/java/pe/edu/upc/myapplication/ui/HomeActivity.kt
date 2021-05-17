package pe.edu.upc.myapplication.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import pe.edu.upc.myapplication.databinding.ActivityHomeBinding


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