package com.smartoffice.client.api.callback.room

import com.smartoffice.client.api.model.room.RoomsResponse

interface RoomCallback {
    fun onGetAllRoomsSuccess(roomsData: List<RoomsResponse>)
    fun onGetAllRoomsFailure(message: String)
}