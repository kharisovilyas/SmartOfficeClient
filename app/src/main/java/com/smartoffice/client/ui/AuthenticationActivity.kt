package com.smartoffice.client.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartoffice.client.R
import com.smartoffice.client.databinding.ActivityAuthenticationBinding
import com.smartoffice.client.ui.auth.fragment.WelcomeFragment

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_auth, WelcomeFragment())
            .commit()
        setContentView(binding.root)
    }
}