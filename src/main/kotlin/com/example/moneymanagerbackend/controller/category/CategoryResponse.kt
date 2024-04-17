package com.example.moneymanagerbackend.controller.category

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed

data class CategoryResponse(
    val id: String,
    val name: String,
    val color: String,
    val userId: String
)