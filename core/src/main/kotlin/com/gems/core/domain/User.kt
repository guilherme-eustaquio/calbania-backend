package com.gems.core.domain
import java.util.UUID

data class User (
    var createdAt : String? = null,
    var id : String = UUID.randomUUID().toString(),
    var firstTime : Boolean? = true,
    var username: String? = null,
    var password: String? = null,
    var level : String? = null,
    var lastToken: String? = null
)