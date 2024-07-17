package com.phobos.application.repository

import com.phobos.infrastructure.persistence.Session
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Int> {
    fun findSessionByUserIdAndMentalDisorderId(userId: Int, mentalDisorderId: Int, sort: Sort): List<Session>
}