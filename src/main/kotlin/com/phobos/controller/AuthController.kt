package com.phobos.controller

import com.phobos.application.config.security.JwtUtil
import com.phobos.application.dto.AuthenticationRequest
import com.phobos.application.dto.AuthenticationResponse
import com.phobos.application.dto.RefreshToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService
) {
    @PostMapping("/login")
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest):
            ResponseEntity<AuthenticationResponse> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authenticationRequest.email, authenticationRequest.password)
        )
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.email)
        val jwt: String = jwtUtil.generateToken(userDetails)
        val refresh = jwtUtil.generateRefreshToken(userDetails)

        return ResponseEntity.ok(AuthenticationResponse(jwt, refresh))
    }

    @PostMapping("/refresh")
    fun refreshAuthenticationToken(@RequestBody refreshToken: RefreshToken): ResponseEntity<AuthenticationResponse> {
        val userDetails =
            jwtUtil.getUserDetailsFromRefreshToken(refreshToken.refreshToken)

        if (jwtUtil.validateRefreshToken(refreshToken.refreshToken, userDetails)) {
            val newAccessToken = jwtUtil.generateToken(userDetails)
            return ResponseEntity.ok(AuthenticationResponse(newAccessToken, refreshToken.refreshToken))
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    }

}