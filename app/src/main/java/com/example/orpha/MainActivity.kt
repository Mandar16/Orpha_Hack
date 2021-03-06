package com.example.orpha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.orpha.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_main)
        binding.registerOrphanageBtn.setOnClickListener {
            val intent = Intent(this,OrphanageRegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.continueBtn.setOnClickListener {
            val intent = Intent(this,OrphanageDisplayActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}