package com.gems.application.exception

import io.javalin.Javalin.log

class AdminLevelException : Exception {
    constructor() : super() {
        log.error("you can't change the admin level")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}