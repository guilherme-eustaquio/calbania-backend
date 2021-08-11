package com.gems.application.handler

import com.gems.application.exception.UserAlreadyExistsException
import com.gems.application.response.ErrorResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

fun conflictExceptionHandlers(app: Javalin) {

    app.exception(UserAlreadyExistsException::class.java) { _, ctx ->
        ctx.status(HttpStatus.CONFLICT_409)
        ctx.json(ErrorResponse("User already exists", HttpStatus.CONFLICT_409))
    }
}