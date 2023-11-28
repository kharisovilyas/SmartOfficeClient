package com.smartoffice.client.ui.main_ui.viewmodel.control

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartoffice.client.api.callback.ApiResponseCallback
import com.smartoffice.client.api.callback.controls.LightingCallback
import com.smartoffice.client.api.domain.controls.LightingController
import com.smartoffice.client.api.model.control.LightingControlRemoteRequest
import com.smartoffice.client.api.model.control.LightingControlRemoteResponse
class UpdateLightingViewModel() : ViewModel() {
    private val lightingController = LightingController()
    private val _lightingData = MutableLiveData<LightingControlRemoteResponse>()

    val lightingData: MutableLiveData<LightingControlRemoteResponse>
        get() = _lightingData

    private val _errorMessage = MutableLiveData<String>()

    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun updateLightingData(request: LightingControlRemoteRequest){
        lightingController.updateLightingData(request, object : ApiResponseCallback {
            override fun onUpdateSuccess(message: String) {
                _errorMessage.postValue(message)
            }
            override fun onUpdateFailure(message: String) {
                _errorMessage.postValue(message)
            }

        })
    }

    fun fetchLightingData(hvacName: String) {
        lightingController.fetchLightingData(hvacName,
            object : LightingCallback {
                override fun onGetLightingDataSuccess(lightingControlRemoteResponse: LightingControlRemoteResponse) {
                    _lightingData.postValue(lightingControlRemoteResponse)
                }
                override fun onGetLightingDataFailure(message: String) {
                    _errorMessage.postValue(message)
                }
            })
    }
}