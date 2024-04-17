package com.example.moneymanagerbackend.controller.category

import com.example.moneymanagerbackend.service.CategoryService
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/category")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategories(@AuthenticationPrincipal userDetails: UserDetails): List<CategoryResponse> {
        return categoryService.getAllCategories(userDetails.username)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCategory(
        @RequestBody categoryRequest: CategoryRequest,
        @AuthenticationPrincipal userDetails: UserDetails
    ): CategoryResponse {
        return categoryService.addCategory(categoryRequest, userDetails.username)
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable categoryId: String): ResponseEntity<String> {
        val id = ObjectId(categoryId)
        val success = categoryService.deleteCategory(id)

        return if (success)
            ResponseEntity.noContent().build()
        else
            throw IllegalArgumentException("Cannot find category")
    }
}