package com.smartoffice.client.api.domain.controls

import android.annotation.SuppressLint
import com.smartoffice.client.api.callback.ApiResponseCallback
import com.smartoffice.client.api.callback.controls.HVACCallback
import com.smartoffice.client.api.client.RetrofitClient
import com.smartoffice.client.api.model.ApiRemoteResponse
import com.smartoffice.client.api.model.control.HVACControlRemoteRequest
import com.smartoffice.client.api.model.control.HVACControlRemoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HVACController() {

    fun updateHVACData(hvacControlRemoteRequest: HVACControlRemoteRequest, apiResponseCallback: ApiResponseCallback){
        val hvacService = RetrofitClient.apiService
        hvacService.updateDataHVAC(hvacControlRemoteRequest).enqueue(object : Callback<ApiRemoteResponse>{
            override fun onResponse(
                call: Call<ApiRemoteResponse>,
                response: Response<ApiRemoteResponse>
            ) {
                apiResponseCallback.onUpdateSuccess(response.message())
            }

            override fun onFailure(call: Call<ApiRemoteResponse>, t: Throwable) {
                apiResponseCallback.onUpdateFailure(t.message!!)
            }

        })
    }

    fun fetchHVACData(hvacName: String, hvacCallback: HVACCallback) {
        val hvacService = RetrofitClient.apiService
        hvacService.fetchHVACData(hvacName).enqueue(object : Callback<HVACControlRemoteResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<HVACControlRemoteResponse>,
                response: Response<HVACControlRemoteResponse>
            ) {
                println(response.body())
                response.body()?.let { hvacCallback.onGetHVACDataSuccess(it) }
            }

            override fun onFailure(call: Call<HVACControlRemoteResponse>, t: Throwable) {
                hvacCallback.onGetHVACDataFailure(t.message!!)
            }
        })
    }
}