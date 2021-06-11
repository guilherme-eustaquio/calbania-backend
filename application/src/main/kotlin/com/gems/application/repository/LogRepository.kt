package com.gems.application.repository

import com.gems.application.config.DatabaseManager
import io.javalin.Javalin.log

object LogRepository {

    fun save(keyName : String, document : Any) {
        log.info("inserting ${document} on log database...")
        DatabaseManager.save(keyName, document)
    }
}