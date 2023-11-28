package com.smartoffice.client.api.model.control

data class HVACControlRemoteRequest(
    val temperature: Double,
    val humidity: Double,
    val roomName: String
)

data class HVACControlRemoteResponse(
    val temperature: Double,
    val humidity: Double,
    val roomName: String
)