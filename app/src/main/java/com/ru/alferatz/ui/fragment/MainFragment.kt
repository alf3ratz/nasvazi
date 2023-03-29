package com.ru.alferatz.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ru.alferatz.R
import com.ru.alferatz.databinding.FragmentBookingBinding
import com.ru.alferatz.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var binding: FragmentMainBinding? = null
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i("blabla", "da")
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val navHostFragment = childFragmentManager.findFragmentById(
            R.id.nav_host_fragment_content_main
        ) as NavHostFragment
        navController = navHostFragment.navController
//        binding!!.bottomNavMain.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding!!.bottomNavMain,
            navController)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}