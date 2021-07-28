package com.gems.application.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.gems.application.repository.TokenRepository
import com.gems.application.utils.DateUtils.plusYear
import com.gems.core.domain.User
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

object JwtProvider {

    private val generator = KeyPairGenerator.getInstance("RSA")
    private val keyPair : KeyPair
    private val algorithm : Algorithm

    init {
        generator.initialize(2048)
        keyPair = generator.generateKeyPair()
        algorithm = setAlgorithm()
    }

    fun generateToken(user: User): String {
        return JWT.create()
            .withClaim("username", user.username)
            .withClaim("level", user.level)
            .withExpiresAt(plusYear())
            .sign(algorithm)
    }

    fun validateToken(token : String): DecodedJWT? {
        return try {
            val verifier = JWT.require(algorithm)
                .build()
            verifier.verify(token)
        } catch (exception: JWTVerificationException) {
            null
        }
    }

    fun storeOnBlackListToken(token : String) {
        TokenRepository.save(token)
    }

    private fun setAlgorithm(): Algorithm {
        return Algorithm.RSA256(keyPair.public as RSAPublicKey?, keyPair.private as RSAPrivateKey?)
    }

}