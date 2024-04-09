package com.example.moneymanagerbackend.controller.user

import io.jsonwebtoken.security.Password

data class UserRequest(
    val username: String,
    val name: String,
    val email: String,
    val password: String
)
