package com.smartoffice.client.api.callback

interface ApiResponseCallback {
    fun onUpdateSuccess(message: String)
    fun onUpdateFailure(message: String)
}