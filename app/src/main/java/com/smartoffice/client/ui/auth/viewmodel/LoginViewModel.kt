package com.smartoffice.client.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartoffice.client.api.callback.auth.LoginCallback
import com.smartoffice.client.api.client.RetrofitClient
import com.smartoffice.client.api.model.user.LoginRequest
import com.smartoffice.client.api.model.user.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private var loginCallback: LoginCallback? = null

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    fun loginUser(loginRequest: LoginRequest) {
        val apiService =
            RetrofitClient.apiService // Предполагается, что у вас есть RetrofitClient с настроенным API сервисом
        apiService.loginUser(loginRequest)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    _loginResult.value = response.isSuccessful
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    _loginResult.value = false
                    println(t)
                }
            })
    }
}