package com.example.moneymanagerbackend.repository

import com.example.moneymanagerbackend.model.Category
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.Optional
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: MongoRepository<Category, ObjectId> {
    @Query("{'name':?0}")
    fun findByName(name: String): Optional<Category>
}