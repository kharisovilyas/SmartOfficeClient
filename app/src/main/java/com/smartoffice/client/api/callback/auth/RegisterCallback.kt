package com.smartoffice.client.api.callback.auth

interface RegisterCallback {
    fun onRegisterSuccess(message: String)
    fun onRegisterFailure(message: String?)
}