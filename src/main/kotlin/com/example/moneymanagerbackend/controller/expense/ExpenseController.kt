package com.example.moneymanagerbackend.controller.expense

import com.example.moneymanagerbackend.model.Expense
import com.example.moneymanagerbackend.service.ExpenseService
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
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

    @GetMapping
    fun getAllExpensesByUserId(@AuthenticationPrincipal userDetails: UserDetails): List<ExpenseResponse> {

        return expenseService.getAllExpenses(userDetails.username)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addExpense(
        @RequestBody expenseRequest: ExpenseRequest,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ExpenseResponse {
        return expenseService.addExpense(expenseRequest, userDetails.username)
    }

    @PutMapping("/{expenseId}")
    fun updateExpense(
        @PathVariable expenseId: ObjectId,
        @RequestBody updatedExpenseRequest: ExpenseRequest
    ): ExpenseResponse {
        return expenseService.updateExpense(expenseId, updatedExpenseRequest)
    }

    @DeleteMapping("/{expenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteExpense(@PathVariable expenseId: ObjectId): ResponseEntity<String> {
        val success = expenseService.deleteExpense(expenseId)

        return if (success)
            ResponseEntity.noContent().build()
        else
            throw IllegalArgumentException("Cannot find expense")
    }
}