package com.phobos.infrastructure.mapper

import com.phobos.application.dto.MentalDisorderResponse
import com.phobos.application.dto.user.UserRequest
import com.phobos.application.dto.user.UserResponse
import com.phobos.infrastructure.persistence.user.User
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UserMapper {
    companion object {
        fun entityToResponse(user: User): UserResponse = UserResponse(
            id = user.id,
            name = user.name,
            email = user.email,
            imageUrl = user.profileImagePath,
            birthdate = user.birthdate,
            telephone = user.telephone,
            address = user.address,
            userType = user.userType,
            mentalDisorders = user.userDisorders.map {
                MentalDisorderResponse(
                    it.mentalDisorder.id,
                    it.mentalDisorder.mentalDisorder
                )
            }
        )

        fun requestToEntity(user: UserRequest, profileImagePath: String?): User = User(
            name = user.name,
            email = user.email,
            profileImagePath = profileImagePath,
            password = user.password, // todo
            birthdate = user.birthdate,
            telephone = user.telephone,
            address = user.address,
            userType = user.userType,
            releaseDate = LocalDate.now(),
            userDisorders = emptyList()
        )
    }
}