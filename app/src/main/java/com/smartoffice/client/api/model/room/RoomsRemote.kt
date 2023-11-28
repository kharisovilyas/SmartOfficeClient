package com.smartoffice.client.api.model.room

import java.time.LocalDateTime

data class RoomsResponse(
    val roomName: String,
    val flor: Int,
    val capacity: Int,
    val workload: Int,
    val timeUpdate: LocalDateTime
)