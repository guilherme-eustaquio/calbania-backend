package com.gems.application.handler.custom

import com.gems.application.exception.RevokedTokenException
import com.gems.application.exception.UsernameOrPasswordIncorrectException
import com.gems.application.response.ErrorResponse
import io.javalin.Javalin
import org.eclipse.jetty.http.HttpStatus

fun unauthorizedExceptionHandlers(app: Javalin) {

    app.exception(RevokedTokenException::class.java) { _, ctx ->
        ctx.status(HttpStatus.UNAUTHORIZED_401)
        ctx.json(ErrorResponse("Token was revoked", HttpStatus.UNAUTHORIZED_401))
    }

    app.exception(UsernameOrPasswordIncorrectException::class.java) { _, ctx ->
        ctx.status(HttpStatus.UNAUTHORIZED_401)
        ctx.json(ErrorResponse("Username or password incorrect", HttpStatus.UNAUTHORIZED_401))
    }
}