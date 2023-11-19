package com.smartoffice.client.ui.auth.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.datepicker.MaterialDatePicker
import com.smartoffice.client.R
import com.smartoffice.client.api.model.RegisterUserData
import com.smartoffice.client.api.model.RegisterUserRequest
import com.smartoffice.client.databinding.InputUserDataDialogBinding
import com.smartoffice.client.ui.auth.viewmodel.RegisterViewModel
import java.text.SimpleDateFormat
import java.util.*

class InputUserDataDialog(
    private val userData: RegisterUserData
) : DialogFragment() {
    private lateinit var binding: InputUserDataDialogBinding

    fun newInstance(): InputUserDataDialog {
        return InputUserDataDialog(userData)
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog = dialog!!
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window?.setLayout(width, height)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InputUserDataDialogBinding.inflate(inflater, container, false)
        binding.showDatePicker.setOnClickListener {
            showDatePicker()
        }
        binding.register.setOnClickListener {
            checkingInputAndRequest()
        }
        return binding.root
    }

    private fun showDatePicker() {
        // Откройте DatePicker для выбора даты рождения
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Выберите дату рождения")
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.timeInMillis = selection
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val birthDate = dateFormat.format(calendar.time)
            val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
            viewModel.setSelectedDate(birthDate)
        }
        datePicker.show(childFragmentManager, datePicker.toString())
    }

    private fun checkingInputAndRequest() {
        val name = binding.inputName.text.toString()
        val surname = binding.inputSurname.text.toString()
        val patronymic = binding.inputSurname.text.toString()
        val company = binding.autoCompleteTextView.text.toString()
        var birthDate = ""
        val email = userData.email
        val password = userData.password
        val viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        viewModel.selectedDate.observe(this) {
            birthDate = it
        }
        // Проверка ввода
        var hasError = false
        if (name.isEmpty()) {
            binding.nameInputLayout.error = "Введите свое имя"
            hasError = true
        } else {
            binding.nameInputLayout.error = null
        }
        if (surname.isEmpty()) {
            binding.surnameInputLayout.error = "Введите свою фамилию"
            hasError = true
        } else {
            binding.surnameInputLayout.error = null
        }
        if (company.isEmpty()) {
            binding.companyInputLayout.error = "Введите компанию"
            hasError = true
        } else {
            binding.companyInputLayout.error = null
        }
        if (birthDate.isEmpty()) {
            binding.showDatePicker.setIconResource(R.drawable.ic_date_error)
            binding.showDatePicker.setStrokeColorResource(R.color.red)
            binding.showDatePicker.strokeWidth = 10
            binding.showErrorDateInput.visibility = View.VISIBLE
        }
        if (hasError) {
            //доделать AlertDialog
        } else {
            // Все поля заполнены, вызовите метод регистрации
            viewModel.registerUser(
                RegisterUserRequest(
                    email=email,
                    password=password,
                    firstName = name,
                    surname = surname,
                    patronymic = patronymic,
                    birthday = birthDate,
                    company=company
                )
            )
        }
    }
}