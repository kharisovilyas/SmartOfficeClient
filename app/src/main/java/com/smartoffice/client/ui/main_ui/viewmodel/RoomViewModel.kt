package com.smartoffice.client.ui.main_ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartoffice.client.api.callback.room.RoomCallback
import com.smartoffice.client.api.domain.room.RoomController
import com.smartoffice.client.api.model.room.RoomsResponse
import com.smartoffice.client.ui.main_ui.home.adapters.RoomsListAdapter

class RoomViewModel() : ViewModel() {
    private val roomController = RoomController()
    private val _roomData = MutableLiveData<List<RoomsResponse>>()

    val roomsData: LiveData<List<RoomsResponse>>
        get() = _roomData

    private val _errorMessage = MutableLiveData<String>()

    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun fetchRooms(adapter: RoomsListAdapter) {
        roomController.fetchRooms(adapter, object : RoomCallback{
            override fun onGetAllRoomsSuccess(roomsData: List<RoomsResponse>) {
                _roomData.postValue(roomsData)
            }

            override fun onGetAllRoomsFailure(message: String) {
                _errorMessage.postValue(message)
            }

        })
    }
}