package com.phobos.application.dto.session

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class SessionResponse(
    val id: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val sessionDate: LocalDate,
    val activationLevel: Int,
    val exposureLevel: Int,
    val userId: Int,
    val mentalDisorderId: Int,
    val progress: Int,
    val therapistNotes: String,
    val userNotes: String
)
