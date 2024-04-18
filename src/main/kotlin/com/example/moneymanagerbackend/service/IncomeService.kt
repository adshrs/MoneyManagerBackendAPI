package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.controller.income.IncomeRequest
import com.example.moneymanagerbackend.controller.income.IncomeResponse
import com.example.moneymanagerbackend.model.Income
import com.example.moneymanagerbackend.repository.IncomeRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class IncomeService(
    private val incomeRepository: IncomeRepository,
    private val userDetailsService: CustomUserDetailsService
) {

    fun getAllIncomes(username: String): List<IncomeResponse> {
        val userId = userDetailsService.getUserIdByUsername(username)
        return incomeRepository.findAllByUserId(userId)
            .map { it.toResponse() }
    }

    fun addIncome(incomeRequest: IncomeRequest, username: String): IncomeResponse {
        val userId = userDetailsService.getUserIdByUsername(username)
        incomeRequest.userId = userId

        val expense = incomeRequest.toModel()
        return incomeRepository.save(expense).toResponse()
    }

    fun updateIncome(incomeId: ObjectId, updatedIncomeRequest: IncomeRequest): IncomeResponse {
        val existingIncome = incomeRepository.findById(incomeId)
            .orElseThrow { NoSuchElementException("Income with ID $incomeId not found") }

        val incomeAfterUpdate = existingIncome.apply {
            amount = updatedIncomeRequest.amount
            date = updatedIncomeRequest.date
            description = updatedIncomeRequest.description
        }

        return incomeRepository.save(incomeAfterUpdate).toResponse()
    }

    fun deleteIncome(incomeId: ObjectId): Boolean =
        if (incomeRepository.existsById(incomeId)) {
            incomeRepository.deleteById(incomeId)
            true
        } else false

    private fun IncomeRequest.toModel(): Income =
        Income(
            id = ObjectId(),
            amount = this.amount,
            date = this.date,
            description = this.description,
            userId = this.userId!!
        )

    private fun Income.toResponse(): IncomeResponse =
        IncomeResponse(
            id = this.id.toString(),
            amount = this.amount,
            date = this.date,
            description = this.description,
            userId = this.userId.toString()
        )
}