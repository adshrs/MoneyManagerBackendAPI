package com.example.moneymanagerbackend.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    val id: ObjectId = ObjectId(),
    @Indexed(unique = true)
    var username: String,
    var name: String,
    @Indexed(unique = true)
    var email: String,
    var password: String,
    val role: Role = Role.USER
)

enum class Role {
    USER, ADMIN
}

