package tk.svsq.newsfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import tk.svsq.newsfeed.databinding.ActivityMainBinding
import tk.svsq.newsfeed.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_fragment)
}