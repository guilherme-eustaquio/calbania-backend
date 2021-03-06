package com.gems.application.exception

import io.javalin.Javalin.log

class UserNotFoundException : Exception {
    constructor() : super() {
        log.error("user not found")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}