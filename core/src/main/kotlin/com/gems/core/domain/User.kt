package com.gems.core.domain

data class User (
    var createdAt : String? = null,
    var id : String? = null,
    var firstTime : Boolean? = true,
    var username: String? = null,
    var password: String? = null,
    var level : String? = null,
    var lastToken: String? = null
)