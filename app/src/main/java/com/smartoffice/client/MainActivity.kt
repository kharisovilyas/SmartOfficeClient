package com.smartoffice.client

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smartoffice.client.databinding.ActivityMainBinding
import com.smartoffice.client.ui.AuthenticationActivity
import com.smartoffice.client.ui.main_ui.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Проверяем, была ли выполнена аутентификация
        if (isUserAuthenticated()) {
            // Если аутентификация выполнена, открываем MainActivity
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment())
                    .commit()
            }
            setContentView(binding.root)
        } else {
            // Если аутентификация не выполнена, открываем AuthenticationActivity
            val intent = Intent(this, AuthenticationActivity::class.java)
            startActivity(intent)
            finish() // Завершаем текущую активность, чтобы пользователь не мог вернуться назад
        }
    }

    private fun isUserAuthenticated(): Boolean {
        val sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isAuthenticated", false)
    }
}
