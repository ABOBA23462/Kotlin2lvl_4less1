package com.example.kotlin2lvl_2less.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlin2lvl_2less.R
import com.example.kotlin2lvl_2less.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private val preferenceHelper = PreferenceHelper()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
        isShown()
    }

    private fun isShown() {
        preferenceHelper.unit(this)
        if (!preferenceHelper.onSaveOnBoardState && !preferenceHelper.saveRegistration) {
            navController.navigate(R.id.noteAppFragment)
        } else {
            navController.navigate(R.id.onBoardFragment)
        }
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}
