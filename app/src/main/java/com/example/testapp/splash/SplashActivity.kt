package com.example.testapp.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.testapp.MainActivity
import com.example.testapp.R
import com.example.testapp.webview.WebViewActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        val i = Intent(this, MainActivity::class.java)

        if (android.os.Build.VERSION.SDK_INT >= 31) {
                startActivity(i)
                finish()
        }
        else{handler.postDelayed(Runnable {
            startActivity(i)
            finish()
        }, 3000)}


    }
}