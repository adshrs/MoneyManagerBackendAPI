package com.example.moneymanagerbackend.repository

import com.example.moneymanagerbackend.model.Category
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: MongoRepository<Category, ObjectId> {

    fun findAllByUserId(userId: ObjectId): List<Category>
    fun existsByNameAndUserId(name: String, userId: ObjectId): Boolean
}