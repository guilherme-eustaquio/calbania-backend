package com.gems.application.exception

import io.javalin.Javalin.log

class InvalidTokenException : Exception {
    constructor() : super() {
        log.error("This token is invalid")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}