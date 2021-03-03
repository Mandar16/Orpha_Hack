package com.example.orpha

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread(Runnable {
            SystemClock.sleep(500)
            runOnUiThread {
                startActivity(Intent(baseContext, LoginActivity::class.java))
                finish()
            }
        }).start()
    }
}