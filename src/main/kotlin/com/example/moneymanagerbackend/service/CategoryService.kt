package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.model.Category
import com.example.moneymanagerbackend.repository.CategoryRepository
import org.bson.types.ObjectId
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun getAllCategoriesByUserId(userId: ObjectId): List<Category> {
        return categoryRepository.findAllByUserId(userId)
    }

    fun addCategory(category: Category): Category {
        if (categoryRepository.existsByNameAndUserId(category.name, category.userId)){
            throw DuplicateKeyException("Category with name '${category.name}' already exists for user ${category.userId}")
        }

        return categoryRepository.save(category)
    }

    fun deleteCategory(categoryId: ObjectId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId)
        } else {
            throw IllegalArgumentException("Category with id $categoryId not found")
        }
    }
}