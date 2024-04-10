package com.example.moneymanagerbackend.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Category(
    @Id
    val id: ObjectId = ObjectId(),
    @Indexed(unique = true)
    val name: String,
    val color: String,
    val userId: ObjectId
)