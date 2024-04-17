package com.example.moneymanagerbackend.controller.user

import com.example.moneymanagerbackend.model.Role
import com.example.moneymanagerbackend.model.User
import com.example.moneymanagerbackend.service.UserService
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {
    @PostMapping
    fun create(@RequestBody userRequest: UserRequest): UserResponse =
        userService.registerUser(
            user = userRequest.toModel()
        )
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Specified username or email already exists")

    @GetMapping
    fun listAll(): List<UserResponse> =
        userService.findAll()
            .map { it.toResponse() }

    @GetMapping("/{id}")
    fun findByID(@PathVariable id: ObjectId): UserResponse =
        userService.findById(id)
            ?.toResponse()
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find a user.")

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: ObjectId): ResponseEntity<String> {
        val success = userService.deleteById(id)

        return if (success)
            ResponseEntity.ok("User deleted")
        else
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find user.")
    }


    private fun UserRequest.toModel(): User =
        User(
            id = ObjectId(),
            username = this.username,
            name = this.name,
            email = this.email,
            password = this.password,
            role = Role.USER
        )

    private fun User.toResponse(): UserResponse =
        UserResponse(
            id = this.id.toString(),
            username = this.username,
            name = this.name,
            email = this.email,
            role = this.role.name
        )

}