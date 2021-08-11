package com.gems.application.service

import com.auth0.jwt.interfaces.DecodedJWT
import com.gems.application.enum.Roles
import com.gems.application.exception.UserAlreadyExistsException
import com.gems.application.exception.UserNotFoundException
import com.gems.application.repository.UserRepository
import com.gems.application.security.AuthProvider
import com.gems.application.security.JwtProvider.validateToken
import com.gems.core.domain.User
import io.javalin.Javalin.log
import java.util.UUID

object UserService {

    fun findByUsername(name : String): User? {
        return UserRepository.findByUserName(name)
    }

    fun findAll(current : Long = 0, limit : Long = -1) : List<User> {
        return UserRepository.findAll(current, limit)
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
        user.id = UUID.randomUUID().toString()
        user.username = "admin"
        user.password = AuthProvider.encryptPassword("calbania")
        user.level = Roles.ADMIN.name
        UserRepository.save(user)
    }

    fun save(user : User): User {

        if(UserRepository.findByUserName(user.username!!) != null) {
            log.error("Error creating user")
            throw UserAlreadyExistsException()
        }

        user.id = UUID.randomUUID().toString()
        user.password = user.password?.let { AuthProvider.encryptPassword(it) }
        return UserRepository.save(user)
    }

    fun update(user : User): User {

        val userFound = user.username?.let { UserRepository.findByUserName(it) }

        if(userFound == null) {
            log.error("Error updating user")
            throw UserNotFoundException()
        }

        return UserRepository.save(userFound) {
            setNotNullAttributes(userFound, user)
        }
    }

    fun updateByToken(token : String, user : User) {

        val decoded : DecodedJWT? = validateToken(token)

        val username = decoded?.getClaim("username")?.asString()

        val userFound = username?.let { UserRepository.findByUserName(it) }

        if (userFound != null) {
            setNotNullAttributes(userFound, user)
        }
    }

    private fun setNotNullAttributes(userToUpdate : User, user : User) {
        user.username?.also { userToUpdate.username = it }
        user.level?.also { userToUpdate.level = it }
        user.password?.also { userToUpdate.password = AuthProvider.encryptPassword(it) }
        user.firstTime.also { userToUpdate.firstTime = it }
    }
}