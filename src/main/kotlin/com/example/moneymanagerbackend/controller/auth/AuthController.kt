package com.example.moneymanagerbackend.controller.auth

import com.example.moneymanagerbackend.service.AuthenticationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authenticationService: AuthenticationService) {

    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse =
        authenticationService.authentication(authRequest)

    private fun String.mapToTokenResponse(): TokenResponse =
        TokenResponse(
            token = this
        )
}