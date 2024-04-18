package com.example.moneymanagerbackend.controller.user

data class UserResponse(
    val id: String,
    val username: String,
    val name: String,
    val email: String,
    val role: String
)
