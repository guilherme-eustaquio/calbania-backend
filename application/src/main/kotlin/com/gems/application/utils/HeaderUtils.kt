package com.gems.application.utils

import com.gems.application.exception.InvalidAuthorizationHeaderException
import io.javalin.http.Context

object HeaderUtils {
    fun getTokenByHeader(ctx : Context): String {

        val header = ctx.header("Authorization")

        if(header != null && header.contains("Bearer ")) {
            return header.removePrefix("Bearer ")
        }

        throw InvalidAuthorizationHeaderException()
    }
}