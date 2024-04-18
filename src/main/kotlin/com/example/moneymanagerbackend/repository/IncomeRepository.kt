package com.example.moneymanagerbackend.repository

import com.example.moneymanagerbackend.model.Expense
import com.example.moneymanagerbackend.model.Income
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface IncomeRepository: MongoRepository<Income, ObjectId> {

    fun findAllByUserId(userId: ObjectId): List<Income>
}