package com.phobos.application.config.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtUtil(
    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expirationMs}")
    private val jwtExpirationMs: Long,

    @Value("\${jwt.refreshExpirationMs}")
    private val jwtRefreshExpirationMs: Long,

    private val phobosUserDetailsService: PhobosUserDetailsService
) {


    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, userDetails.username, jwtExpirationMs)
    }

    fun generateRefreshToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, userDetails.username, jwtRefreshExpirationMs)
    }

    private fun createToken(claims: Map<String, Any>, subject: String, expirationMs: Long): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }

    fun validateToken(token: String?, userDetails: UserDetails): Boolean {
        return if (token == null) {
            false
        } else {
            val username = extractUsername(token)
            (username == userDetails.username && !isTokenExpired(token))
        }
    }

    fun validateRefreshToken(token: String, userDetails: UserDetails): Boolean {
        return validateToken(token, userDetails) // Add any additional validation if needed
    }

    fun getUserDetailsFromRefreshToken(token: String): UserDetails {
        val username = extractUsername(token)
        return phobosUserDetailsService.loadUserByUsername(username)
    }

}