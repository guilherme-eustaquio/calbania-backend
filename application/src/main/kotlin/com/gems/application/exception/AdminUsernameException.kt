package com.gems.application.exception

import io.javalin.Javalin.log

class AdminUsernameException : Exception {
    constructor() : super() {
        log.error("you can't rename the admin username")
    }
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}