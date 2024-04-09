package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.model.User
import com.example.moneymanagerbackend.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder
) {

    fun registerUser(user: User): User? {
        val foundUsername = userRepository.findByUsername(user.username)
        val foundEmail = userRepository.findByEmail(user.email)

        return if (foundUsername == null && foundEmail == null) {
            val updatedUser = user.copy(password = encoder.encode(user.password))

            userRepository.save(updatedUser)
            user
        } else null
    }

    fun findById(id: ObjectId): User? =
        userRepository.findByIdOrNull(id)

    fun findAll(): List<User> =
        userRepository.findAll()

    fun deleteById(id: ObjectId): Boolean =
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            true
        } else false

}