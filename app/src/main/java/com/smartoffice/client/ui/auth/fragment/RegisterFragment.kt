package com.smartoffice.client.ui.auth.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartoffice.client.api.model.RegisterUserData
import com.smartoffice.client.databinding.RegisterFragmentBinding
import com.smartoffice.client.ui.auth.dialog.InputUserDataDialog

class RegisterFragment : Fragment() {
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        binding.registerStart.setOnClickListener {
            checkingInputAndStartDialog()
        }
        return binding.root
    }

    private fun checkingInputAndStartDialog() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPassword.text.toString()
        // Проверяем, что email и пароль не пусты
        if (email.isNotEmpty() && password.isNotEmpty()) {
            // Ваши действия при вводе данных
            startInputDialog(
                RegisterUserData(email, password)
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

    }

    private fun startInputDialog(userData: RegisterUserData) {
        val fragment = InputUserDataDialog(
            userData
        ).newInstance()
        fragment.show(requireActivity().supportFragmentManager, "input")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
    }
}