package com.ru.alferatz.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.ru.alferatz.R
import com.ru.alferatz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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



}