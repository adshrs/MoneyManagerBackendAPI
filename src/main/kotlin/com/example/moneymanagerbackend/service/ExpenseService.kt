package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.model.Expense
import com.example.moneymanagerbackend.repository.ExpenseRepository
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Exp
import org.springframework.stereotype.Service

@Service
class ExpenseService(private val expenseRepository: ExpenseRepository) {

    fun getAllExpensesByUserId(userId: ObjectId): List<Expense> {
        return expenseRepository.findAllByUserId(userId)
    }

    fun addExpense(expense: Expense): Expense {
        return expenseRepository.save(expense)
    }

    fun updateExpense(expenseId: ObjectId, updatedExpense: Expense): Expense {
        val existingExpense = expenseRepository.findById(expenseId)
            .orElseThrow { NoSuchElementException("Expense with ID $expenseId not found") }

        val expenseAfterUpdate = existingExpense.apply {
            amount = updatedExpense.amount
            date = updatedExpense.date
            recurrence = updatedExpense.recurrence
            description = updatedExpense.description
            categoryId = updatedExpense.categoryId
        }

        return expenseRepository.save(expenseAfterUpdate)
    }

    fun deleteExpense(expenseId: ObjectId) {
        val existingExpense = expenseRepository.findById(expenseId)
            .orElseThrow { NoSuchElementException("Expense with ID $expenseId not found") }

        expenseRepository.delete(existingExpense)
    }
}