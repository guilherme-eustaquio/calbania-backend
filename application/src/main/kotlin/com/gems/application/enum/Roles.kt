package com.gems.application.enum

import io.javalin.core.security.Role

enum class Roles : Role {
    ANYONE, USER, ADMIN
}