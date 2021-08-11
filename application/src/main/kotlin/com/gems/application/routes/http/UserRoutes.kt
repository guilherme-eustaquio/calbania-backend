package com.gems.application.routes.http

import com.gems.application.enum.Roles
import com.gems.application.request.UserRequest
import com.gems.application.request.UserUpdateTokenRequest
import com.gems.application.response.UserResponse
import com.gems.application.service.UserService
import com.gems.application.utils.HeaderUtils
import com.gems.application.utils.JsonUtils.map
import com.gems.application.utils.JsonUtils.mapAll
import com.gems.core.domain.User
import io.javalin.Javalin
import io.javalin.core.security.SecurityUtil.roles

fun beginUserHttpRoutes(app : Javalin) {

    val contextPath = "/users"

    app.post(contextPath, { ctx ->
        val user = map(ctx.body<UserRequest>(), User::class.java)
        ctx.json(map(UserService.save(user), UserResponse::class.java))
    }, roles(Roles.ADMIN))

    app.put("$contextPath/:id", { ctx ->
        val user = map(ctx.body<UserRequest>(), User::class.java)
        user.id = ctx.pathParam("id")
        ctx.json(map(UserService.update(user), UserResponse::class.java))
    }, roles(Roles.ADMIN))

    app.put(contextPath) { ctx ->
        val user = map(ctx.body<UserUpdateTokenRequest>(), User::class.java)
        val token = HeaderUtils.getTokenByHeader(ctx)
        ctx.json(map(UserService.updateByToken(token, user), UserResponse::class.java))
    }

    app.get(contextPath) { ctx ->
        ctx.json(mapAll(UserService.findAll(), UserResponse::class.java))
    }

}