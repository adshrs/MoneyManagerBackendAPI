package com.example.moneymanagerbackend.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.userdetails.UserDetails

@Document
data class RefreshToken(
    @Id
    val token: String,
    val username: String
)
