package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.controller.expense.ExpenseRequest
import com.example.moneymanagerbackend.controller.expense.ExpenseResponse
import com.example.moneymanagerbackend.model.Expense
import com.example.moneymanagerbackend.repository.ExpenseRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository,
    private val userDetailsService: CustomUserDetailsService
) {

    fun getAllExpenses(username: String): List<ExpenseResponse> {
        val userId = userDetailsService.getUserIdByUsername(username)
        return expenseRepository.findAllByUserId(userId)
            .map { it.toResponse() }
    }

    fun addExpense(expenseRequest: ExpenseRequest, username: String): ExpenseResponse {
        val userId = userDetailsService.getUserIdByUsername(username)
        expenseRequest.userId = userId

        val expense = expenseRequest.toModel()
        return expenseRepository.save(expense).toResponse()
    }

    fun updateExpense(expenseId: ObjectId, updatedExpenseRequest: ExpenseRequest): ExpenseResponse {
        val existingExpense = expenseRepository.findById(expenseId)
            .orElseThrow { NoSuchElementException("Expense with ID $expenseId not found") }

        val expenseAfterUpdate = existingExpense.apply {
            amount = updatedExpenseRequest.amount
            date = updatedExpenseRequest.date
            description = updatedExpenseRequest.description
            categoryId = ObjectId(updatedExpenseRequest.categoryId)
        }

        return expenseRepository.save(expenseAfterUpdate).toResponse()
    }

    fun deleteExpense(expenseId: ObjectId): Boolean =
        if (expenseRepository.existsById(expenseId)) {
            expenseRepository.deleteById(expenseId)
            true
        } else false

    private fun ExpenseRequest.toModel(): Expense =
        Expense(
            id = ObjectId(),
            amount = this.amount,
            date = this.date,
            description = this.description,
            categoryId = ObjectId(this.categoryId),
            userId = this.userId!!
        )

    private fun Expense.toResponse(): ExpenseResponse =
        ExpenseResponse(
            id = this.id.toString(),
            amount = this.amount,
            date = this.date,
            description = this.description,
            categoryId = this.categoryId.toString(),
            userId = this.userId.toString()
        )
}