package com.gems.application.routes.http

import com.gems.application.enum.Roles
import com.gems.application.repository.UserRepository
import com.gems.application.request.UserLoginRequest
import com.gems.application.response.JwtResponse
import com.gems.application.security.AuthProvider
import com.gems.application.security.JwtProvider
import com.gems.application.utils.JsonUtils.toJsonObject
import com.gems.application.utils.JsonUtils.toJsonString
import com.gems.core.domain.User
import io.javalin.Javalin
import io.javalin.core.security.SecurityUtil.roles

fun beginAuthHttpRoutes(app : Javalin) {

    val contextPath = "/auth"

    app.post("$contextPath/login", { ctx ->

        val user = toJsonObject(toJsonString(ctx.body<UserLoginRequest>()), User::class.java)
        val userToCompare = AuthProvider.checkPassword(user)
        val token = JwtProvider.generateToken(userToCompare)

        userToCompare.lastToken?.let {
            JwtProvider.storeOnBlackListToken(it)
        }

        userToCompare.lastToken = token

        UserRepository.save(userToCompare)

        ctx.json(JwtResponse(token))
    }, roles(Roles.ANYONE))
}