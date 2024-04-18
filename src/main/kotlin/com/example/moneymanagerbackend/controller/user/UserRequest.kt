package com.example.moneymanagerbackend.controller.user

data class UserRequest(
    val username: String,
    val name: String,
    val email: String,
    val password: String
)
