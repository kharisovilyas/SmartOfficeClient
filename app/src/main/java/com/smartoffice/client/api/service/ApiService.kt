package com.smartoffice.client.api.service

import com.smartoffice.client.api.model.*
import com.smartoffice.client.api.model.control.HVACControlRemoteRequest
import com.smartoffice.client.api.model.control.HVACControlRemoteResponse
import com.smartoffice.client.api.model.control.LightingControlRemoteRequest
import com.smartoffice.client.api.model.control.LightingControlRemoteResponse
import com.smartoffice.client.api.model.room.RoomsResponse
import com.smartoffice.client.api.model.user.LoginRequest
import com.smartoffice.client.api.model.user.LoginResponse
import com.smartoffice.client.api.model.user.RegisterResponse
import com.smartoffice.client.api.model.user.RegisterUserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    //room
    @GET("api/room/get/all")
    fun getAllRooms(): Call<List<RoomsResponse>>

    @POST("api/control/lighting/update")
    fun updateDataLighting(@Body request: LightingControlRemoteRequest): Call<ApiRemoteResponse>

    @GET("api/control/lighting/get")
    fun fetchDataLighting(@Query("lightingName") lightingName: String): Call<LightingControlRemoteResponse>

    @POST("api/control/hvac/update")
    fun updateDataHVAC(@Body request: HVACControlRemoteRequest): Call<ApiRemoteResponse>

    @GET("api/control/hvac/get")
    fun fetchHVACData(@Query("hvacName") hvacName: String): Call<HVACControlRemoteResponse>

    //auth
    @POST("register")
    fun registerUser(@Body request: RegisterUserRequest): Call<RegisterResponse>

    @POST("login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>


}