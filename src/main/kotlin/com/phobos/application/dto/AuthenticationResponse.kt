package com.phobos.application.dto

import com.phobos.application.dto.user.UserResponse

data class AuthenticationResponse(
    val token: String,
    val refreshToken: String,
    val user: UserResponse
)
