package com.gems.application.handler.custom

import com.gems.application.exception.UserNotFoundException
import com.gems.application.response.ErrorResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

fun notFoundExceptionHandlers(app: Javalin) {

    app.exception(UserNotFoundException::class.java) { _, ctx ->
        ctx.status(HttpStatus.NOT_FOUND_404)
        ctx.json(ErrorResponse("User not found", HttpStatus.NOT_FOUND_404))
    }
}