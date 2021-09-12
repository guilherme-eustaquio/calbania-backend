package com.gems.application.exception

import io.javalin.Javalin.log

class LevelException : Exception {
    constructor() : super() {
        log.error("You don't have permission to perform this action")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}