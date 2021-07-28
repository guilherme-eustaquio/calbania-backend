package com.gems.application.handler

import com.gems.application.response.ErrorResponse
import io.javalin.Javalin
import io.javalin.Javalin.log
import io.javalin.http.BadRequestResponse
import io.javalin.http.ForbiddenResponse
import io.javalin.http.NotFoundResponse
import io.javalin.http.UnauthorizedResponse
import org.eclipse.jetty.http.HttpStatus
import java.lang.Exception

fun globalExceptionHandlers(app: Javalin) {

    app.exception(BadRequestResponse::class.java) { _, ctx ->
        ctx.status(HttpStatus.BAD_REQUEST_400)
        ctx.json(ErrorResponse("Bad request", HttpStatus.BAD_REQUEST_400))
    }

    app.exception(UnauthorizedResponse::class.java) { _, ctx ->
        ctx.status(HttpStatus.UNAUTHORIZED_401)
        ctx.json(ErrorResponse("Unauthorized", HttpStatus.UNAUTHORIZED_401))
    }

    app.exception(ForbiddenResponse::class.java) { _, ctx ->
        ctx.status(HttpStatus.FORBIDDEN_403)
        ctx.json(ErrorResponse("Forbidden", HttpStatus.FORBIDDEN_403))
    }

    app.exception(NotFoundResponse::class.java) { _, ctx ->
        ctx.status(HttpStatus.NOT_FOUND_404)
        ctx.json(ErrorResponse("Not found", HttpStatus.NOT_FOUND_404))
    }

    app.exception(Exception::class.java) {_, ctx ->
        ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500)
    }.error(HttpStatus.INTERNAL_SERVER_ERROR_500) { ctx ->
        ctx.json(ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR_500))
    }
}