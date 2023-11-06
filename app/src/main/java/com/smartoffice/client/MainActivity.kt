package com.smartoffice.client

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartoffice.client.databinding.ActivityMainBinding
import com.smartoffice.client.ui.AuthenticationActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)
        setContentView(binding.root)
    }
}
