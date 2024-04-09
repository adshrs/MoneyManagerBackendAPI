package com.example.moneymanagerbackend.repository

import com.example.moneymanagerbackend.model.Expense
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepository: MongoRepository<Expense, ObjectId> {
    fun findAllByUserId(userId: ObjectId): List<Expense>
}