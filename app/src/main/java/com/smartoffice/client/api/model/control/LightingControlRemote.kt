package com.smartoffice.client.api.model.control
data class LightingControlRemoteRequest(
    val controlName: String,
    val brightness: Int,
    val colorTemperature: Int,
)

data class LightingControlRemoteResponse(
    val controlName: String,
    val brightness: Int,
    val colorTemperature: Int,
)