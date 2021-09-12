package template

import com.gems.application.enum.Roles
import com.gems.application.security.AuthProvider
import com.gems.core.domain.User
import java.util.*
import kotlin.collections.ArrayList

object UserMock {

    fun createUser(encryptPassword : Boolean = true) : User {

        val user = User()
        user.id = UUID.randomUUID().toString()
        user.username = UUID.randomUUID().toString()
        user.password = "guest"
        user.level = Roles.ADMIN.name
        user.firstTime = true

        if(encryptPassword) {
            user.password = AuthProvider.encryptPassword(user.password!!)
        }

        return user
    }

    fun createUserList(size : Long = 10): List<User> {
        val users = ArrayList<User>()

        for (i in 1..size) {
            users.add(createUser())
        }

        return users
    }
}