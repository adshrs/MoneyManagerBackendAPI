package com.example.moneymanagerbackend.controller.income

import com.example.moneymanagerbackend.service.IncomeService
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/income")
class IncomeController(private val incomeService: IncomeService) {

    @GetMapping
    fun getAllIncomesByUserId(@AuthenticationPrincipal userDetails: UserDetails): List<IncomeResponse> {

        return incomeService.getAllIncomes(userDetails.username)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addIncome(
        @RequestBody incomeRequest: IncomeRequest,
        @AuthenticationPrincipal userDetails: UserDetails
    ): IncomeResponse {
        return incomeService.addIncome(incomeRequest, userDetails.username)
    }

    @PutMapping("/{incomeId}")
    fun updateIncome(
        @PathVariable incomeId: ObjectId,
        @RequestBody updatedIncomeRequest: IncomeRequest
    ): IncomeResponse {
        return incomeService.updateIncome(incomeId, updatedIncomeRequest)
    }

    @DeleteMapping("/{incomeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteIncome(@PathVariable incomeId: ObjectId): ResponseEntity<String> {
        val success = incomeService.deleteIncome(incomeId)

        return if (success)
            ResponseEntity.noContent().build()
        else
            throw IllegalArgumentException("Cannot find income")
    }
}