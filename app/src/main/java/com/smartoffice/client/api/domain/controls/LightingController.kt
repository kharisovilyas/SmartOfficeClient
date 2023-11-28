package com.smartoffice.client.api.domain.controls

import android.annotation.SuppressLint
import com.smartoffice.client.api.callback.ApiResponseCallback
import com.smartoffice.client.api.callback.controls.HVACCallback
import com.smartoffice.client.api.callback.controls.LightingCallback
import com.smartoffice.client.api.client.RetrofitClient
import com.smartoffice.client.api.model.ApiRemoteResponse
import com.smartoffice.client.api.model.control.HVACControlRemoteResponse
import com.smartoffice.client.api.model.control.LightingControlRemoteRequest
import com.smartoffice.client.api.model.control.LightingControlRemoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LightingController  {

    fun updateLightingData(lightingControlRemoteRequest: LightingControlRemoteRequest,
                           apiResponseCallback: ApiResponseCallback){
        val hvacService = RetrofitClient.apiService
        hvacService.updateDataLighting(lightingControlRemoteRequest).enqueue(object :
            Callback<ApiRemoteResponse> {
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

    fun fetchLightingData(lightingName: String, lightingCallback: LightingCallback) {
        val hvacService = RetrofitClient.apiService
        hvacService.fetchDataLighting(lightingName).enqueue(object : Callback<LightingControlRemoteResponse> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(
                call: Call<LightingControlRemoteResponse>,
                response: Response<LightingControlRemoteResponse>
            ) {
                println(response.body())
                response.body()?.let { lightingCallback.onGetLightingDataSuccess(it) }
            }

            override fun onFailure(call: Call<LightingControlRemoteResponse>, t: Throwable) {
                lightingCallback.onGetLightingDataFailure(t.message!!)
            }
        })
    }
}