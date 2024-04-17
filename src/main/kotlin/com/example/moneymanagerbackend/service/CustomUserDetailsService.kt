package com.example.moneymanagerbackend.service

import com.example.moneymanagerbackend.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.example.moneymanagerbackend.model.User

@Service
class CustomUserDetailsService(private val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("Username not found!")

    fun getUserIdByUsername(username: String): ObjectId {
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("Username not found!")

        return user.id
    }

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.username)
            .password(this.password)
            .roles(this.role.name)
            .build()
}