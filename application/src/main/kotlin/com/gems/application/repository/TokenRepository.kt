package com.gems.application.repository

import com.gems.application.config.DatabaseManager

object TokenRepository {

    private const val keyName = "blacklist_tokens"

    fun save(document : Any) {
        DatabaseManager.save(keyName, document)
    }

    fun findByToken(token : String): String? {

        val tokens : ArrayList<String> = DatabaseManager.findByKeyName(keyName, String::class.java)

        return tokens.find { blacklistToken ->
            blacklistToken == token
        }
    }
}