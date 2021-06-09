package com.gems.application.config

import com.gems.application.util.DatabaseCredentials.databaseName
import com.gems.application.util.DatabaseCredentials.uri
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object DatabaseConfig {

    private val connection : CoroutineClient = KMongo.createClient(uri).coroutine
    private val database : CoroutineDatabase = connection.getDatabase(databaseName)

    fun getCollection(collectionName : String): CoroutineCollection<Any> {
        return database.getCollection(collectionName)
    }
}
