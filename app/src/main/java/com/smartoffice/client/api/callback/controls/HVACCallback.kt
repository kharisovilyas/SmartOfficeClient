package com.smartoffice.client.api.callback.controls

import com.smartoffice.client.api.model.control.HVACControlRemoteResponse

interface HVACCallback {
    fun onGetHVACDataSuccess(hvacControlRemoteResponse: HVACControlRemoteResponse)
    fun onGetHVACDataFailure(message: String)
}