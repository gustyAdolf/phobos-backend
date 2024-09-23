package com.phobos.controller

import com.phobos.application.config.security.JwtUtil
import com.phobos.application.dto.AuthenticationRequest
import com.phobos.application.dto.AuthenticationResponse
import com.phobos.application.service.UserService
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
    private val userDetailsService: UserDetailsService,
    private val userService: UserService
) {
    @PostMapping("/login")
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest):
            ResponseEntity<AuthenticationResponse> {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authenticationRequest.email, authenticationRequest.password)
        )
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.email)
        val jwt: String = jwtUtil.generateToken(userDetails, true)
        val refresh = jwtUtil.generateRefreshToken(userDetails, true)
        val userResponse = userService.getUserByEmail(authenticationRequest.email)
        return ResponseEntity.ok(
            AuthenticationResponse(
                token = jwt,
                refreshToken = refresh,
                user = userResponse
            )
        )
    }

}