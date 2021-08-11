package com.gems.application.exception

class InvalidAuthorizationHeaderException : Exception {
    constructor() : super()
    constructor(message : String) : super(message)
    constructor(message : String, cause : Throwable) : super(message, cause)
    constructor(cause : Throwable) : super(cause)
}