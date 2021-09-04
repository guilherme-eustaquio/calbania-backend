package com.gems.application.exception

import io.javalin.Javalin.log

class UserAlreadyExistsException : Exception {
    constructor() : super() {
        log.error("user already exists")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}