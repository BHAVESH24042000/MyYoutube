package com.example.myyoutube.authScreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myyoutube.R
import kotlinx.android.synthetic.main.activity_check_user.*

class CheckUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_user)

        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }

        val navController: NavController =findNavController(R.id.navHostfragment2)
        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.signupFragment,

                )
        )

        bottomNavigationView2.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }
}