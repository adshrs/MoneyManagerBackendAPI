package com.example.moneymanagerbackend.controller.category

data class CategoryRequest(
    val name: String,
    val color: String,
    var userId: String
)
