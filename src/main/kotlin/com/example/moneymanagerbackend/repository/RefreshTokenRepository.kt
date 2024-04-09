package com.example.moneymanagerbackend.repository

import com.example.moneymanagerbackend.model.RefreshToken
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository: MongoRepository<RefreshToken, String> {
    fun findByToken(token: String): RefreshToken?
}