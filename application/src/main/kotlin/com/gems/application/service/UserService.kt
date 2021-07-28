package com.gems.application.service

import com.gems.application.enum.Roles
import com.gems.application.repository.UserRepository
import com.gems.application.security.AuthProvider
import com.gems.core.domain.User
import io.javalin.Javalin.log

object UserService {

    fun findByUsername(name : String): User? {
        return UserRepository.findByUserName(name)
    }

    fun findById(id : String): User? {
        return UserRepository.findById(id)
    }

    fun createAdminUser() {

        if(UserRepository.findByUserName("admin") != null) {
            log.info("admin already created.")
            return
        }

        log.info("creating admin user...")
        val user = User()
        user.username = "admin"
        user.password = AuthProvider.encryptPassword("calbania")
        user.level = Roles.ADMIN.name
        UserRepository.save(user)
    }

    fun save(user : User) {
        user.password = user.password?.let { AuthProvider.encryptPassword(it) }
        UserRepository.save(user)
    }
}