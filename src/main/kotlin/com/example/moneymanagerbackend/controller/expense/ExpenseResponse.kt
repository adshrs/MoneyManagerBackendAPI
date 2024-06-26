package com.example.moneymanagerbackend.controller.expense

data class ExpenseResponse(
    val id: String,
    var amount: Double,
    var date: String,
    var description: String,
    var categoryId: String,
    val userId: String
)
