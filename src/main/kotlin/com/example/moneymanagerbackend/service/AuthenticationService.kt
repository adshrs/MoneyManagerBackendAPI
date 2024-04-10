package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.config.JwtProperties
import com.example.moneymanagerbackend.controller.auth.AuthenticationRequest
import com.example.moneymanagerbackend.controller.auth.AuthenticationResponse
import com.example.moneymanagerbackend.model.RefreshToken
import com.example.moneymanagerbackend.repository.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.username,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.username)

        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)

        refreshTokenRepository.save(RefreshToken(refreshToken, user.username))

        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun refreshAccessToken(token: String): String? {
        val extractedUsername = tokenService.extractUsername(token)

        return extractedUsername?.let { username ->
            val currentUserDetails = userDetailsService.loadUserByUsername(username)
            val refreshToken = refreshTokenRepository.findByToken(token)

            if (!tokenService.isExpired(token) && refreshToken?.username == currentUserDetails.username)
                generateAccessToken(currentUserDetails)
            else
                null
        }
    }

    fun logout(username: String) {
        refreshTokenRepository.deleteByUsername(username)
    }

    private fun generateRefreshToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
    )

    private fun generateAccessToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    )
}
