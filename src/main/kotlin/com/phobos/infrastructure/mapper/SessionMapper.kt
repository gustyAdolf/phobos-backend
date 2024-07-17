package com.phobos.infrastructure.mapper

import com.phobos.application.dto.session.SessionRequest
import com.phobos.application.dto.session.SessionResponse
import com.phobos.infrastructure.persistence.Session
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class SessionMapper {
    companion object {
        fun entityToResponse(session: Session): SessionResponse = SessionResponse(
            id = session.id,
            sessionDate = session.sessionDate,
            activationLevel = session.activationLevel,
            exposureLevel = session.exposureLevel,
            userId = session.userId,
            mentalDisorderId = session.mentalDisorderId,
            progress = session.progress,
            therapistNotes = session.therapistNotes,
            userNotes = session.userNotes
        )

        fun requestToEntity(request: SessionRequest): Session = Session(
            sessionDate = LocalDate.now(),
            activationLevel = request.activationLevel,
            exposureLevel = request.exposureLevel,
            userId = request.userId,
            mentalDisorderId = request.mentalDisorderId,
            progress = request.progress,
            therapistNotes = request.therapistNotes,
            userNotes = request.userNotes
        )
    }
}