package com.phobos.application.dto

data class AuthenticationResponse(
    val token: String,
    val refreshToken: String,
)
