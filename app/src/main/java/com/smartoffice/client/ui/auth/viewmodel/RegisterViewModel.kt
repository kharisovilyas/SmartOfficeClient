package com.smartoffice.client.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartoffice.client.api.callback.RegisterCallback
import com.smartoffice.client.api.client.RetrofitClient
import com.smartoffice.client.api.model.RegisterResponse
import com.smartoffice.client.api.model.RegisterUserData
import com.smartoffice.client.api.model.RegisterUserRequest
import com.smartoffice.client.api.service.ApiService
import com.smartoffice.client.ui.auth.dialog.InputUserDataDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.*

class RegisterViewModel : ViewModel() {

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String>
        get() = _selectedDate

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    private var registerCallback: RegisterCallback? = null

    private val _registrationResult = MutableLiveData<Boolean>()
    val registrationResult: LiveData<Boolean>
        get() = _registrationResult

    fun registerUser(registerUserRequest: RegisterUserRequest) {
        val apiService =
            RetrofitClient.apiService // Предполагается, что у вас есть RetrofitClient с настроенным API сервисом
        apiService.registerUser(registerUserRequest)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    _registrationResult.value = response.isSuccessful
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    _registrationResult.value = false
                }
            })
    }

    fun setRegisterCallback(callback: RegisterCallback) {
        registerCallback = callback
    }

    fun clearRegisterCallback() {
        registerCallback = null
    }

}
