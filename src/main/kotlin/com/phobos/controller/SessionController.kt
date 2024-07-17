package com.phobos.controller

import com.phobos.application.dto.session.SessionRequest
import com.phobos.application.dto.session.SessionResponse
import com.phobos.application.service.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/session")
class SessionController(@Autowired val sessionService: SessionService) {

    @GetMapping
    fun getSessionBy(
        @RequestParam(value = "userId", required = true) userId: Int,
        @RequestParam(value = "mentalDisorderId", required = true) mentalDisorderId: Int,
        @RequestParam(value = "dateOrder", defaultValue = "desc") dateOrder: String
    ): ResponseEntity<List<SessionResponse>> {
        val sessions = sessionService.getSessionsBy(userId, mentalDisorderId, dateOrder)
        return ResponseEntity.ok(sessions)
    }

    @PostMapping
    fun newSession(@RequestBody session: SessionRequest): ResponseEntity<Void> {
        sessionService.addSession(session)
        return ResponseEntity.ok().build()
    }
}