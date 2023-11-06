package com.smartoffice.client.ui.auth


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartoffice.client.R
import com.smartoffice.client.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WelcomeFragmentBinding.inflate(inflater, container, false)

        binding.loginStart.setOnClickListener {
            // Обработчик нажатия на кнопку "Login"
            val loginFragment = LoginFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_auth, loginFragment)
                .commit()
        }

        binding.registerStart.setOnClickListener {
            // Обработчик нажатия на кнопку "Register"
            val registerFragment = RegisterFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_auth, registerFragment)
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }
}
