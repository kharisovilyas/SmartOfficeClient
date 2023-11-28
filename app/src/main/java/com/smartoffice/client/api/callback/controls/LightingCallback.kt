package com.smartoffice.client.api.callback.controls

import com.smartoffice.client.api.model.control.HVACControlRemoteResponse
import com.smartoffice.client.api.model.control.LightingControlRemoteResponse

interface LightingCallback {
    fun onGetLightingDataSuccess(lightingControlRemoteResponse: LightingControlRemoteResponse)
    fun onGetLightingDataFailure(message: String)
}