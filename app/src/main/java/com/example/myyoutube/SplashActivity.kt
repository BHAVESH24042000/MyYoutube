package com.example.myyoutube

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ProgressBar
import com.example.myyoutube.authScreens.CheckUserActivity
import com.example.myyoutube.databinding.ActivityMainBinding
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Wave
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityMainBinding.inflate(layoutInflater)

        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }

        setContentView(_binding!!.root)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController!!.hide(WindowInsets.Type.statusBars())
        }else{
            @Suppress("DEPRECATION")
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        val progressBar = _binding!!.spinKit as ProgressBar
        val wave: Sprite = Wave()
        progressBar.indeterminateDrawable = wave

        Handler(Looper.getMainLooper()).postDelayed({

            val user= FirebaseAuth.getInstance().currentUser
            if(user!=null){
                val intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, CheckUserActivity::class.java)
                startActivity(intent)
                finish()
            }


        },3828)

    }

}