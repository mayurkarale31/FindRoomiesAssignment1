package com.bitcodetech.findroomies.spashactivity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bitcodetech.findroomies.auth.activity.Login_Activity
import com.bitcodetech.findroomies.databinding.SplashActivityBinding

class SplashActivity : AppCompatActivity(){
    private lateinit var binding : SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, Login_Activity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}