package com.phobos.application.dto.user

import com.phobos.infrastructure.enums.UserTypeEnum
import java.time.LocalDate

data class UserRequest(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val birthdate: LocalDate = LocalDate.now(),
    val telephone: String = "",
    val address: String = "",
    val userType: UserTypeEnum = UserTypeEnum.PATIENT,
    val mentalDisorders: List<Int> = emptyList()
)