package com.example.moneymanagerbackend.controller.category

import com.example.moneymanagerbackend.model.Category
import com.example.moneymanagerbackend.service.CategoryService
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/category")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/{userId}")
    fun getAllCategoriesByUserId(@PathVariable userId: ObjectId): List<Category> {
        return categoryService.getAllCategoriesByUserId(userId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCategory(@RequestBody category: Category): Category {
        return categoryService.addCategory(category)
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCategory(@PathVariable categoryId: ObjectId) {
        categoryService.deleteCategory(categoryId)
    }
}