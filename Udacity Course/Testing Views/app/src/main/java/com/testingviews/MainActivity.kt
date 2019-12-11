package com.testingviews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bottomNavigation = bottom_navigation as BottomNavigationView
        bottomNavigation.setOnNavigationItemReselectedListener{ item ->
            when(item.itemId) {
                R.id.nav_home -> {}
                R.id.nav_favorites -> {}
                R.id.nav_search -> {}
            }
            return@setOnNavigationItemReselectedListener
        }

    }


}
