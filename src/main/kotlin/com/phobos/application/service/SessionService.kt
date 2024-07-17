package com.phobos.application.service

import com.phobos.application.dto.session.SessionRequest
import com.phobos.application.dto.session.SessionResponse
import com.phobos.application.repository.SessionRepository
import com.phobos.infrastructure.mapper.SessionMapper
import com.phobos.infrastructure.persistence.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SessionService(
    @Autowired private val sessionRepository: SessionRepository
) {
    @Transactional(readOnly = true)
    fun getSessionsBy(userId: Int, mentalDisorderId: Int, dateOrder: String): List<SessionResponse> {
        val sort = if (dateOrder.equals("asc", ignoreCase = true)) {
            Sort.by("sessionDate").ascending()
        } else {
            Sort.by("sessionDate").descending()
        }
        return sessionRepository.findSessionByUserIdAndMentalDisorderId(userId, mentalDisorderId, sort)
            .map { SessionMapper.entityToResponse(it) }
    }

    @Transactional
    fun addSession(session: SessionRequest) {
        val newSession: Session = SessionMapper.requestToEntity(session)
        sessionRepository.save(newSession)
    }
}