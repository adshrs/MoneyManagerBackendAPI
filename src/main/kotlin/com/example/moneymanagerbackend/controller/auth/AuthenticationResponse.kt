package com.example.moneymanagerbackend.controller.auth

data class AuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
