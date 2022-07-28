package com.example.nikeapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.nikeapp.R
import com.example.nikeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            //Navigation
            navController = findNavController(R.id.nav_host)
            bottomNav.setupWithNavController(navController)

            //Show Bottom Navigation
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.detailFragment -> bottomNav.visibility = View.GONE
                    else -> bottomNav.visibility = View.VISIBLE
                }
            }

        }
    }


    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}