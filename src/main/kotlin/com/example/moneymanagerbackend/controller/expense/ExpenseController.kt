package com.example.moneymanagerbackend.controller.expense

import com.example.moneymanagerbackend.model.Expense
import com.example.moneymanagerbackend.service.ExpenseService
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/expense")
class ExpenseController(private val expenseService: ExpenseService) {
    @GetMapping("/{userId}")
    fun getAllExpensesByUserId(@PathVariable userId: ObjectId): List<Expense> {
        return expenseService.getAllExpensesByUserId(userId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addExpense(@RequestBody expense: Expense): Expense {
        return expenseService.addExpense(expense)
    }

    @PutMapping("/{expenseId}")
    fun updateExpense(
        @PathVariable expenseId: ObjectId,
        @RequestBody updatedExpense: Expense
    ): Expense {
        return expenseService.updateExpense(expenseId, updatedExpense)
    }

    @DeleteMapping("/{expenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteExpense(@PathVariable expenseId: ObjectId) {
        expenseService.deleteExpense(expenseId)
    }
}