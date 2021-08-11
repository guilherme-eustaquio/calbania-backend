package com.gems.application.config

import com.gems.application.enum.Roles
import com.gems.application.exception.RevokedTokenException
import com.gems.application.repository.TokenRepository
import com.gems.application.security.JwtProvider.validateToken
import com.gems.application.utils.HeaderUtils.getTokenByHeader
import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.Handler
import io.javalin.http.UnauthorizedResponse

object AccessManagerConfig {

    private fun accessManager(handler: Handler, ctx: Context, permittedRoles: Set<Role>) {
        when {
            checkIfRoleIsAnyone(permittedRoles) -> handler.handle(ctx)
            checkToken(ctx, permittedRoles) -> handler.handle(ctx)
            else -> throw UnauthorizedResponse()
        }
    }

    private fun checkIfRoleIsAnyone(permittedRoles: Set<Role>): Boolean {
        return permittedRoles.contains(Roles.ANYONE)
    }

    private fun checkIfUserIsAuthorized(permittedRoles: Set<Role>, level : String) : Boolean {

        if(permittedRoles.isEmpty()) {
            return true
        }

        val role = Roles.values().find { value ->
            level == value.name
        }

        return permittedRoles.contains(role)
    }

    private fun checkToken(ctx: Context, permittedRoles: Set<Role>): Boolean {

        val token = getTokenByHeader(ctx)

        TokenRepository.findByToken(token)?.let {
            throw RevokedTokenException()
        }

        val decoded = validateToken(token)

        if(decoded != null && checkIfUserIsAuthorized(permittedRoles, decoded.getClaim("level").asString())) {
            return true
        }

        return false
    }

    fun setJwtAccessManager(app : Javalin) {
        app.config.accessManager(AccessManagerConfig::accessManager)
    }
}