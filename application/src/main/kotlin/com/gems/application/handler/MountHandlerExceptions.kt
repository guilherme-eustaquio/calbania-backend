package com.gems.application.handler

import com.gems.application.handler.custom.*
import io.javalin.Javalin
import io.javalin.Javalin.log

fun mountHandlerExceptions(app : Javalin) {
    log.info("mounting handler exceptions...")
    globalExceptionHandlers(app)
    notFoundExceptionHandlers(app)
    unauthorizedExceptionHandlers(app)
    conflictExceptionHandlers(app)
    badRequestExceptionHandlers(app)
    forbiddenExceptionHandlers(app)
}