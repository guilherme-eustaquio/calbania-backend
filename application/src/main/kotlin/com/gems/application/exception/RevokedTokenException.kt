package com.gems.application.exception

import io.javalin.Javalin.log

class RevokedTokenException : Exception {
    constructor() : super() {
        log.error("the token was revoked")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}