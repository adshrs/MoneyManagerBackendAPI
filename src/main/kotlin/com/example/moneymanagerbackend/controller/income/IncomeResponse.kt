package com.example.moneymanagerbackend.controller.income

data class IncomeResponse(
    val id: String,
    var amount: Double,
    var date: String,
    var description: String,
    val userId: String
)