package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.controller.category.CategoryRequest
import com.example.moneymanagerbackend.controller.category.CategoryResponse
import com.example.moneymanagerbackend.model.Category
import com.example.moneymanagerbackend.repository.CategoryRepository
import org.bson.types.ObjectId
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val userDetailsService: CustomUserDetailsService
) {

    fun getAllCategories(username: String): List<CategoryResponse> {
        val userId = userDetailsService.getUserIdByUsername(username)
        return categoryRepository.findAllByUserId(userId)
            .map { it.toResponse() }
    }

    fun addCategory(categoryRequest: CategoryRequest, username: String): CategoryResponse {
        val userId = userDetailsService.getUserIdByUsername(username)
        categoryRequest.userId = userId.toString()

        val category = categoryRequest.toModel()

        if (categoryRepository.existsByNameAndUserId(category.name, category.userId)) {
            throw DuplicateKeyException("Category with name '${category.name}' already exists")
        }
        return categoryRepository.save(category).toResponse()
    }

    fun deleteCategory(categoryId: ObjectId): Boolean =
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId)
            true
        } else false

    private fun CategoryRequest.toModel(): Category =
        Category(
            id = ObjectId(),
            name = this.name,
            color = this.color,
            userId = ObjectId(this.userId)
        )

    private fun Category.toResponse(): CategoryResponse =
        CategoryResponse(
            id = this.id.toString(),
            name = this.name,
            color = this.color,
            userId = this.userId.toString()
        )
}