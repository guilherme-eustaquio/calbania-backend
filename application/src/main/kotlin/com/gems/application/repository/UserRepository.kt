package com.gems.application.repository

import com.gems.application.config.DatabaseManager
import com.gems.core.domain.User

object UserRepository {

    private const val keyName = "users"

    fun save(user: User, extraModifications: (() -> Unit)? = null): User {

        DatabaseManager.transaction {
            DatabaseManager.delete(keyName, user, 1, it)
            if (extraModifications != null) {
                extraModifications()
            }
            DatabaseManager.save(keyName, user, it)
        }

        return user
    }

    fun findByUserName(name: String): User? {
        val users : ArrayList<User> = DatabaseManager.findByKeyName(keyName, User::class.java, 0, -1)
        return users.find { user ->
            user.username == name
        }
    }

    fun findById(id : String) : User? {
        val users : ArrayList<User> = DatabaseManager.findByKeyName(keyName, User::class.java, 0, -1)
        return users.find { user ->
            user.id == id
        }
    }

    fun findAll(start : Long = 1, stop : Long = -1) : List<User> {
        return DatabaseManager.findByKeyName(keyName, User::class.java, start, stop)
    }
}