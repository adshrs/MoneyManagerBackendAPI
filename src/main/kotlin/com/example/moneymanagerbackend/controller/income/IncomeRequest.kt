package com.example.moneymanagerbackend.controller.income

import org.bson.types.ObjectId

data class IncomeRequest(
    var amount: Double,
    var date: String,
    var description: String,
    var userId: ObjectId?
)
