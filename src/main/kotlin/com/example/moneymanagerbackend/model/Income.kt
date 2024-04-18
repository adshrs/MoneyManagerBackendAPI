package com.example.moneymanagerbackend.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Income(
    @Id
    val id: ObjectId = ObjectId(),
    var amount: Double,
    var date: String,
    var description: String,
    val userId: ObjectId
)