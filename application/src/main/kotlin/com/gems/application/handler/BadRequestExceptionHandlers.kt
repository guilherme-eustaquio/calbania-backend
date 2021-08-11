package com.gems.application.handler

import com.gems.application.exception.InvalidAuthorizationHeaderException
import com.gems.application.response.ErrorResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

fun badRequestExceptionHandlers(app: Javalin) {

    app.exception(InvalidAuthorizationHeaderException::class.java) { _, ctx ->
        ctx.status(HttpStatus.BAD_REQUEST_400)
        ctx.json(ErrorResponse("Something wrong on header 'Authorization'", HttpStatus.BAD_REQUEST_400))
    }
}