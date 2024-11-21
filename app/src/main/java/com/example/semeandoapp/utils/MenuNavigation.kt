package com.example.semeandoapp.utils

import android.content.Context
import android.content.Intent
import com.example.semeandoapp.R
import com.example.semeandoapp.activities.*
import com.google.android.material.bottomnavigation.BottomNavigationView

object MenuNavigation {

    fun setupBottomNavigation(context: Context, bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                    true
                }
                R.id.nav_user -> {
                    val intent = Intent(context, UserPageActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                    true
                }
                R.id.nav_devs -> {
                    val intent = Intent(context, DevsPageActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context.startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}