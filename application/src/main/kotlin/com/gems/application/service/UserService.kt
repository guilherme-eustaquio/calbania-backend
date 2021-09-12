package com.gems.application.service

import com.auth0.jwt.interfaces.DecodedJWT
import com.gems.application.config.DatabaseManager
import com.gems.application.enum.Roles
import com.gems.application.exception.AdminLevelException
import com.gems.application.exception.AdminUsernameException
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

    fun findAll(start : Long = 1, stop : Long = -1) : List<User> {
        return UserRepository.findAll(start, stop)
    }

    fun findById(id : String): User? {
        return UserRepository.findById(id)
    }

    fun createAdminUser(): User? {

        if(UserRepository.findByUserName("admin") != null) {
            log.info("admin already created.")
            return null
        }

        log.info("creating admin user...")
        val user = User()
        user.id = UUID.randomUUID().toString()
        user.username = "admin"
        user.password = AuthProvider.encryptPassword("calbania")
        user.level = Roles.ADMIN.name
        val admin = UserRepository.save(user)
        log.info(DatabaseManager.findByKeyName("users", User::class.java).toString())
        return admin
    }

    fun save(user : User): User {

        if(UserRepository.findByUserName(user.username!!) != null) {
            throw UserAlreadyExistsException()
        }

        user.id = UUID.randomUUID().toString()
        user.password = user.password?.let { AuthProvider.encryptPassword(it) }
        return UserRepository.save(user)
    }

    fun updateCredentials(user : User) : User {
        val userFound = user.username?.let { UserRepository.findByUserName(it) } ?: throw UserNotFoundException()

        return UserRepository.save(userFound) {
            setNotNullAttributes(userFound, user)
        }
    }

    fun update(user : User): User {

        val userFound = user.id?.let { UserRepository.findById(it) } ?: throw UserNotFoundException()

        preventUpdateAdminUsername(userFound.username!!, user.username!!)
        preventUpdateAdminLevel(userFound, user)

        return UserRepository.save(userFound) {
            setNotNullAttributes(userFound, user)
        }
    }

    fun updateByToken(token : String, user : User): User {

        val decoded : DecodedJWT? = validateToken(token)

        val username = decoded?.getClaim("username")?.asString()

        preventUpdateAdminUsername(username!!, user.username!!)

        if(username != user.username && UserRepository.findByUserName(user.username!!) != null) {
            throw UserAlreadyExistsException()
        }

        val userFound = username.let { UserRepository.findByUserName(it) } ?: throw UserNotFoundException()

        return UserRepository.save(userFound) {
            setNotNullAttributes(userFound, user)
        }
    }

    private fun preventUpdateAdminUsername(username : String, usernameToCompare : String) {
        if(username == "admin" && usernameToCompare != username) throw AdminUsernameException()
    }

    private fun preventUpdateAdminLevel(user : User, userToCompare : User) {
        if(user.username == "admin" && userToCompare.level != Roles.ADMIN.name) throw AdminLevelException()
    }

    private fun setNotNullAttributes(userToUpdate : User, user : User) {
        user.username?.also { userToUpdate.username = it }
        user.level?.also { userToUpdate.level = it }
        user.lastToken?.also { userToUpdate.lastToken = it }
        user.password?.also { userToUpdate.password = AuthProvider.encryptPassword(it) }
        user.firstTime.also { userToUpdate.firstTime = it }
    }
}