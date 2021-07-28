package com.gems.application.security

import at.favre.lib.crypto.bcrypt.BCrypt
import com.gems.application.exception.UserNotFoundException
import com.gems.application.exception.UsernameOrPasswordIncorrectException
import com.gems.application.service.UserService
import com.gems.core.domain.User

object AuthProvider {

    fun checkPassword(user : User): User {

        val userToCompare = UserService.findByUsername(user.username!!) ?: throw UserNotFoundException()

        if(!checkPasswordHash(user.password, userToCompare.password)) {
            throw UsernameOrPasswordIncorrectException()
        }

        return userToCompare
    }

    private fun checkPasswordHash(password: String?, passwordToCompare: String?) = BCrypt.verifyer().verify(password?.toCharArray(), passwordToCompare).verified
    fun encryptPassword(password : String): String? = BCrypt.withDefaults().hashToString(6, password.toCharArray())
}