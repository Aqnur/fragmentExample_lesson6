package com.example.fragmentexample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.fragmentexample.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        initBottomNav()
        initToolbar()
        initOnDestinationChangedListener()
        initNavigationView()
    }

    private fun initOnDestinationChangedListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.firstFragment,
                R.id.secondFragment -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
                R.id.blankFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
            }
        }
    }

    private fun initToolbar() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.firstFragment,
                R.id.secondFragment,
            ),
            binding.drawerLayout
        )

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun initNavigationView() {
        binding.navigationView.setupWithNavController(navController)
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                else -> {
                    closeDrawer()
                    val result = NavigationUI.onNavDestinationSelected(it, navController)
                    binding.navigationView.setCheckedItem(it.itemId)
                    result
                }
            }
        }
    }

    private fun initBottomNav() {
        binding.bottomNavigation.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED
        binding.bottomNavigation.setupWithNavController(navController)
    }

    private fun closeDrawer() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(100)
            binding.drawerLayout.closeDrawer(binding.navigationView, true)
        }
    }

}