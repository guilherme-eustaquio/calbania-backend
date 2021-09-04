package com.gems.application.exception

import io.javalin.Javalin.log

class InvalidAuthorizationHeaderException : Exception {
    constructor() : super() {
        log.error("incorrect header for authorization")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}