package com.gems.application.repository

import com.gems.application.config.DatabaseConfig
import io.javalin.Javalin.log
import kotlinx.coroutines.runBlocking
import org.litote.kmongo.coroutine.CoroutineCollection

object LogRepository {

    fun save(document : Any) {
        log.info("inserting ${document} on log database...")
        runBlocking {
            getCollection(document).insertOne(document)
            log.info("inserted.")
        }
    }

    private fun getCollection(document : Any): CoroutineCollection<Any> {
        val entityName = document.javaClass.simpleName.toLowerCase()
        return DatabaseConfig.getCollection(entityName)
    }
}