package com.gems.application.routes.http

import com.gems.application.enum.Roles
import com.gems.application.request.UserRequest
import com.gems.application.request.UserUpdateTokenRequest
import com.gems.application.response.UserResponse
import com.gems.application.security.JwtProvider
import com.gems.application.service.UserService
import com.gems.application.utils.HeaderUtils
import com.gems.application.utils.JsonUtils.map
import com.gems.application.utils.JsonUtils.mapAll
import com.gems.application.utils.JsonUtils.paginate
import com.gems.core.domain.User
import io.javalin.Javalin
import io.javalin.Javalin.log
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
        log.info("starting update user")
        ctx.json(map(UserService.updateByToken(token, user), UserResponse::class.java))
        log.info("user updated successfully")
        log.info("storing token in blacklist")
        JwtProvider.storeOnBlackListToken(token)
    }

    app.get(contextPath) { ctx ->

        val start = ctx.queryParam("start", "0").toString().toLong()
        val stop = ctx.queryParam("stop", "10").toString().toLong()

        ctx.json(
            paginate(mapAll(UserService.findAll(start, stop), UserResponse::class.java), start, stop)
        )
    }

}