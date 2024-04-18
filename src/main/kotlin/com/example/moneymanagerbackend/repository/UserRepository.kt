package com.example.moneymanagerbackend.repository

import com.example.moneymanagerbackend.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<User, ObjectId> {
    fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?
}