package com.phobos.application.dto.user

import com.fasterxml.jackson.annotation.JsonFormat
import com.phobos.application.dto.MentalDisorderResponse
import com.phobos.infrastructure.enums.UserTypeEnum
import java.time.LocalDate

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String,
    val imageUrl: String?,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val birthdate: LocalDate,
    val telephone: String?,
    val address: String?,
    val userType: UserTypeEnum,
    val mentalDisorders: List<MentalDisorderResponse>
)
