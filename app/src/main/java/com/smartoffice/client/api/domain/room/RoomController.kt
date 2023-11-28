package com.smartoffice.client.api.domain.room

import android.annotation.SuppressLint
import com.smartoffice.client.api.callback.room.RoomCallback
import com.smartoffice.client.api.client.RetrofitClient
import com.smartoffice.client.api.model.room.RoomsResponse
import com.smartoffice.client.ui.main_ui.home.adapters.RoomsListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomController() {
    fun fetchRooms(adapter: RoomsListAdapter, roomCallback: RoomCallback){
        val roomService = RetrofitClient.apiService
        roomService.getAllRooms().enqueue(object : Callback<List<RoomsResponse>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<RoomsResponse>>, response: Response<List<RoomsResponse>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.setItemsInStart(it)
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    println(response.message())
                    roomCallback.onGetAllRoomsFailure(response.message())
                }
            }

            override fun onFailure(call: Call<List<RoomsResponse>>, t: Throwable) {
                println(t)
            }
        })
    }
}