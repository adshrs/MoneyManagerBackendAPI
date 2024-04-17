package com.example.moneymanagerbackend.controller.expense

import org.bson.types.ObjectId
import java.time.LocalDate

data class ExpenseRequest(
    var amount: Double,
    var date: String,
    var description: String,
    var categoryId: String,
    var userId: ObjectId?
)
