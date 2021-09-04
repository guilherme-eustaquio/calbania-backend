package com.gems.application.exception

import io.javalin.Javalin.log

class UsernameOrPasswordIncorrectException : Exception {
    constructor() : super() {
        log.error("user with incorrect username or password")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}