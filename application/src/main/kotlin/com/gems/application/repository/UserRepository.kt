package com.gems.application.repository

import com.gems.application.config.DatabaseManager
import com.gems.core.domain.User

object UserRepository {

    private const val keyName = "users"

    fun save(user : User) {

        val userFound = user.id?.let { findById(it) }

        if(userFound != null) {
            setAttributesNotNull(user, userFound)
        }

        DatabaseManager.save(keyName, user)
    }

    fun findByUserName(name: String): User? {
        val users : ArrayList<User> = DatabaseManager.findByKeyName(keyName, User::class.java)
        return users.find { user ->
            user.username == name
        }
    }

    fun findById(id : String) : User? {
        val users : ArrayList<User> = DatabaseManager.findByKeyName(keyName, User::class.java)
        return users.find { user ->
            user.id == id
        }
    }

    private fun setAttributesNotNull(userToUpdate : User, user : User) {
        userToUpdate.username = user.username
        userToUpdate.password = user.password
    }
}