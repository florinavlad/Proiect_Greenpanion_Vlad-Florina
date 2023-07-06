package com.example.greenpanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController
//
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.introFragment) {
//                return@addOnDestinationChangedListener
//            }
//            navController.navigate(R.id.introFragment)
//        }
    }
}
