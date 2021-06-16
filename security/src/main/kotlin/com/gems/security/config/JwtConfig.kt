package com.gems.security.config

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.gems.core.domain.User

object JwtConfig {

    private val algorithm: Algorithm = Algorithm.HMAC256("XbbZZpvDf9xB8JeV1F1nsqbW4TqcP8yZ")

    fun generateToken(user : User): String {
        return JWT.create()
            .withClaim("name", user.name)
            .withClaim("level", user.level)
            .sign(algorithm)
    }

    fun verifyToken(token : String): DecodedJWT? {
        val verifier: JWTVerifier = JWT.require(algorithm)
            .build()
        return verifier.verify(token)
    }
}