package com.smartoffice.client.api.model

data class RegisterUserData(
    val email: String,
    val password: String
)

data class RegisterUserRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val surname: String,
    val patronymic: String,
    val birthday: String,
    val companyName: String,
)

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val token: String
)