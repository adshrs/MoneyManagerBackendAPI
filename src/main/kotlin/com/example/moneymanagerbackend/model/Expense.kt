package com.example.moneymanagerbackend.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class Expense(
    @Id
    val id: ObjectId = ObjectId(),
    var amount: Double,
    var date: String,
    var description: String,
    var categoryId: ObjectId,
    val userId: ObjectId
)