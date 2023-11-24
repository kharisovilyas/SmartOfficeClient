package com.smartoffice.client.ui.auth.fragment.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smartoffice.client.R
import com.smartoffice.client.api.model.RegisterUserData
import com.smartoffice.client.databinding.RegisterFragmentBinding
import com.smartoffice.client.ui.auth.dialog.InputUserDataDialog
import com.smartoffice.client.ui.auth.fragment.home.HomeFragment
import com.smartoffice.client.ui.auth.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RegisterFragmentBinding.inflate(inflater, container, false)
        binding.registerStart.setOnClickListener {
            checkingInputAndStartDialog()
            observeDataForQuitFragment()
        }
        return binding.root
    }

    private fun observeDataForQuitFragment() {
        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        viewModel.registrationResult.observe(viewLifecycleOwner) { registrationResult ->
            if (registrationResult) {
                quitFragment()
            } else {
                println(registrationResult)
            }

        }
    }

    @SuppressLint("CommitTransaction")
    private fun quitFragment() {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_auth, HomeFragment())
            .addToBackStack("NO")
            .commit()
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