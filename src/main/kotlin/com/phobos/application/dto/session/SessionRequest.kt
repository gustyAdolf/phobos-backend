package com.phobos.application.dto.session

data class SessionRequest(
    val activationLevel: Int,
    val exposureLevel: Int,
    val userId: Int,
    val mentalDisorderId: Int,
    val progress: Int,
    val therapistNotes: String,
    val userNotes: String
)
