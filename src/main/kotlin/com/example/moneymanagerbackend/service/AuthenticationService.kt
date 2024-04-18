package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.config.JwtProperties
import com.example.moneymanagerbackend.controller.auth.AuthenticationRequest
import com.example.moneymanagerbackend.controller.auth.AuthenticationResponse
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

        return AuthenticationResponse(
            accessToken = accessToken
        )
    }

    private fun generateAccessToken(user: UserDetails) = tokenService.generate(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    )
}
