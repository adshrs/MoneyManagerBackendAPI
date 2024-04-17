package com.example.moneymanagerbackend.controller.category

import org.bson.types.ObjectId

data class CategoryRequest(
    val name: String,
    val color: String,
    var userId: String
)
