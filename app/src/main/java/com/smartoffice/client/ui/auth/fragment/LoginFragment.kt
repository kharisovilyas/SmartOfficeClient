package com.smartoffice.client.ui.auth.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smartoffice.client.R
import com.smartoffice.client.api.model.user.LoginRequest
import com.smartoffice.client.databinding.LoginFragmentBinding
import com.smartoffice.client.ui.auth.viewmodel.LoginViewModel
import com.smartoffice.client.ui.main_ui.MainFragment

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.authStart.setOnClickListener{
            checkingDataAndRequest()
        }
        return binding.root
    }

    private fun checkingDataAndRequest() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        // Проверяем, что email и пароль не пусты
        val viewModel: LoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        if (email.isNotEmpty() && password.isNotEmpty()) {
            // Ваши действия при вводе данных
            viewModel.loginUser(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
        } else {
            // Подсвечиваем TextInputLayout, если поля не заполнены
            if (email.isEmpty()) {
                binding.emailInputLayout.error = "Введите email"
            } else {
                binding.emailInputLayout.error = null
            }

            if (password.isEmpty()) {
                binding.passwordInputLayout.error = "Введите пароль"
            } else {
                binding.passwordInputLayout.error = null
            }
        }
        viewModel.loginResult.observe(viewLifecycleOwner) { loginResult ->
            if (loginResult) {
                saveAuthenticationStatus(loginResult)
                quitFragment()
            } else {
                //выводить Alert Dialog
            }
        }

    }

    @SuppressLint("CommitTransaction")
    fun quitFragment() {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_auth, MainFragment())
            .commit()
    }
    private fun saveAuthenticationStatus(loginResultStatus: Boolean) {
        val sharedPreferences = requireActivity().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isAuthenticated", loginResultStatus)
        editor.apply()
    }
}