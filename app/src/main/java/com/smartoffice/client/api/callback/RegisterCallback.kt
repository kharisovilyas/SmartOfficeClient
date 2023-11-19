package com.smartoffice.client.api.callback

interface RegisterCallback {
    fun onRegisterSuccess(message: String)
    fun onRegisterFailure(message: String?)
}