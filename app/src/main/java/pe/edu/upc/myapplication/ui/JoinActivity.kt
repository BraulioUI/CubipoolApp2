package pe.edu.upc.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.upc.myapplication.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}