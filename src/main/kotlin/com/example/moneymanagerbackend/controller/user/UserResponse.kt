package com.example.moneymanagerbackend.controller.user

import com.example.moneymanagerbackend.model.Role
import org.bson.types.ObjectId

data class UserResponse(
    val id: String,
    val username: String,
    val name: String,
    val email: String,
    val role: String
)
