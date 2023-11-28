package com.smartoffice.client.ui.main_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartoffice.client.R
import com.smartoffice.client.databinding.MainFragmentBinding
import com.smartoffice.client.ui.main_ui.calendar.CalendarFragment
import com.smartoffice.client.ui.main_ui.chat.ChatFragment
import com.smartoffice.client.ui.main_ui.home.RoomsFragment
import com.smartoffice.client.ui.main_ui.profile.ProfileFragment

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    companion object {
        const val TAG = "MainFragment"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        replaceFragment(RoomsFragment())
        updateToolbarTitle("Главная")
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val fragment = RoomsFragment()
                    replaceFragment(fragment)
                    updateToolbarTitle("Главная")
                    true
                }
                R.id.navigation_chat -> {
                    // Замените ChatFragment на ваш фрагмент
                    val fragment = ChatFragment()
                    replaceFragment(fragment)
                    updateToolbarTitle("Чат с администрацией")
                    true
                }
                R.id.navigation_calendar -> {
                    // Замените CalendarFragment на ваш фрагмент
                    val fragment = CalendarFragment()
                    replaceFragment(fragment)
                    updateToolbarTitle("События")
                    true
                }
                R.id.navigation_profile -> {
                    // Замените ProfileFragment на ваш фрагмент
                    val fragment = ProfileFragment()
                    replaceFragment(fragment)
                    updateToolbarTitle("Мой профиль")
                    true
                }
                else -> false
            }
        }

        return binding.root
    }
    private fun replaceFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null) // Добавьте в стек возврата, если нужно
        transaction.commit()
    }
    private fun updateToolbarTitle(title: String) {
        binding.toolbarTitle.text = title
    }

    fun updateLeftButton(iconResId: Int, clickListener: View.OnClickListener?) {
        binding.btnLeft.setImageResource(iconResId)
        if (clickListener != null) {
            binding.btnLeft.setOnClickListener(clickListener)
        }
    }

    fun updateRightButton(iconResId: Int, clickListener: View.OnClickListener?) {
        binding.btnRight.setImageResource(iconResId)
        if (clickListener != null) {
            binding.btnRight.setOnClickListener(clickListener)
        }
    }
}