package com.ru.alferatz.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ru.alferatz.R
import com.ru.alferatz.databinding.ActivityMainBinding
import com.ru.alferatz.ui.fragment.CurrentBookingFragment
import com.ru.alferatz.ui.fragment.InfoFragment
import com.ru.alferatz.ui.fragment.MainFragment
import com.ru.alferatz.ui.fragment.booking.BookingFragment
import java.security.InvalidParameterException


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    lateinit var menu: BottomNavigationView
    private var oldId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val navHostFragment = supportFragmentManager.findFragmentById(
//            R.id.nav_host_activity_main
//        ) as NavHostFragment
//        navController = navHostFragment.navController
//        menu = findViewById(R.id.bottom_nav)
//        binding.bottomNav.apply {
//            menu
//        }
//        navController = Navigation.findNavController(this, R.id.nav_host_activity_main)
//        NavigationUI.setupWithNavController(binding.bottomNav,navController)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_activity_main) as NavHostFragment
//        val navController = navHostFragment.navController
//        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
//        navView.setupWithNavController(navController)
        val navigationBar = findViewById<BottomNavigationView>(R.id.bottom_nav)
        //Set the click listener on nav bar.
        navigationBar.setOnNavigationItemSelectedListener {
            val frag = when (it.itemId) {
                R.id.action_main -> BookingFragment()
                R.id.action_request -> CurrentBookingFragment()
                R.id.action_info -> InfoFragment()
                else -> throw InvalidParameterException()
            }
            if (oldId != -1 || oldId != it.itemId) {
                moveToFragment(frag)
                oldId = it.itemId
            }
            true
        }
        moveToFragment(BookingFragment())
    }

    private fun moveToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        if (isCurrentUserSignedOut()) {
            Log.i("auth_user", "da")
            //navController.navigate(R.id.action_MainFragment_to_AuthFragment)
        }
    }

    private fun isCurrentUserSignedOut(): Boolean {
//        val user = Firebase.auth.currentUser
//        return user == null
        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        return !appSettingPrefs.getBoolean("signIn", false)
    }

//    private fun setUpBottomNav() {
//        val bottomNavBar by lazy {
//            binding!!.bottomNav
//        } //findViewById<BottomNavigationView>(R.id.bottom_nav)
//        bottomNavBar.setupWithNavController(navController)
//    }


}