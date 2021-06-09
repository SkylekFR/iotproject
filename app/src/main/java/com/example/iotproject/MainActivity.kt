package com.example.iotproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navigationVIew = findViewById<BottomNavigationView>(R.id.main_bottom_navigation_view)
        navigationVIew.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.navigation_home_fragment)
                    // Respond to navigation item 1 click
                    true
                }
                R.id.graphs -> {
                    // Respond to navigation item 2 click
                    navController.navigate(R.id.navigation_stats_fragment)
                    true
                }
                else -> false
            }
        }
    }
}