package com.gems.application.handler.custom

import com.gems.application.exception.AdminUsernameException
import com.gems.application.response.ErrorResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

fun forbiddenExceptionHandlers(app: Javalin) {
    app.exception(AdminUsernameException::class.java) { _, ctx ->
        ctx.status(HttpStatus.FORBIDDEN_403)
        ctx.json(ErrorResponse("You can't change the administrator username", HttpStatus.FORBIDDEN_403))
    }
}