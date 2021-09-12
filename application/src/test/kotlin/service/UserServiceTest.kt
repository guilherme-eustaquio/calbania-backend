package service

import com.gems.application.repository.UserRepository
import com.gems.application.service.UserService
import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import template.UserMock


class UserServiceTest {

    private val user = UserMock.createUser()
    private val users = UserMock.createUserList()

    init {
        mockkObject(UserRepository)
    }

    @Test
    fun `when we find the user by username`() {
        every { UserRepository.findByUserName(user.username!!) } returns user
        val userFound = UserService.findByUsername(user.username!!)
        assertEquals(userFound, user)
    }

    @Test
    fun `when we didn't find the user by username `() {
        every { UserRepository.findByUserName(user.username!!) } returns null
        val userFound = UserService.findByUsername(user.username!!)
        assertNotEquals(userFound, user)
    }

    @Test
    fun `when we want to the list all users`() {
        every { UserRepository.findAll() } returns users
        val usersFound = UserService.findAll()
        assertEquals(usersFound, users)
    }

    @Test
    fun `when we want to the list part of the users`() {
        val partUsers = UserMock.createUserList(5)
        every { UserRepository.findAll(0, 5) } returns partUsers
        val usersFound = UserService.findAll(0, 5)
        assertEquals(usersFound, partUsers)
    }

    @Test
    fun `when we find the user by id`() {
        every { UserRepository.findById(user.id!!) } returns user
        val userFound = UserService.findById(user.id!!)
        assertEquals(userFound, user)
    }

    @Test
    fun `when we didn't find the user by id`() {
        every { UserRepository.findById(user.id!!) } returns null
        val userFound = UserService.findById(user.id!!)
        assertNotEquals(userFound, user)
    }
}