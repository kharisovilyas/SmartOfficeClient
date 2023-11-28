package com.smartoffice.client.ui.main_ui.viewmodel.control

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartoffice.client.api.callback.ApiResponseCallback
import com.smartoffice.client.api.callback.controls.HVACCallback
import com.smartoffice.client.api.callback.room.RoomCallback
import com.smartoffice.client.api.domain.controls.HVACController
import com.smartoffice.client.api.domain.room.RoomController
import com.smartoffice.client.api.model.control.HVACControlRemoteRequest
import com.smartoffice.client.api.model.control.HVACControlRemoteResponse
import com.smartoffice.client.api.model.room.RoomsResponse
import com.smartoffice.client.ui.main_ui.home.adapters.RoomsListAdapter

class UpdateHVACViewModel() : ViewModel() {
    private val hvacController = HVACController()
    private val _hvacData = MutableLiveData<HVACControlRemoteResponse>()

    val hvacData: MutableLiveData<HVACControlRemoteResponse>
        get() = _hvacData

    private val _errorMessage = MutableLiveData<String>()

    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun updateHvacData(request: HVACControlRemoteRequest){
        hvacController.updateHVACData(request, object : ApiResponseCallback{
            override fun onUpdateSuccess(message: String) {
                _errorMessage.postValue(message)
            }
            override fun onUpdateFailure(message: String) {
                _errorMessage.postValue(message)
            }

        })
    }

    fun fetchHvacData(hvacName: String) {
        hvacController.fetchHVACData(hvacName,
            object : HVACCallback {
                override fun onGetHVACDataSuccess(hvacControlRemoteResponse: HVACControlRemoteResponse) {
                    _hvacData.postValue(hvacControlRemoteResponse)
                }

                override fun onGetHVACDataFailure(message: String) {
                    _errorMessage.postValue(message)
                    println(message + "HELLO")
                }
            })
    }
}