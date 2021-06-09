package com.gems.application.changelog

import com.github.cloudyrock.mongock.ChangeLog
import com.github.cloudyrock.mongock.ChangeSet
import com.mongodb.client.MongoDatabase

@ChangeLog
class Changelog {
    @ChangeSet(order = "001", id = "createInitialCollections", author = "Guilherme")
    fun createInitialCollections(mongoDatabase: MongoDatabase) {
        mongoDatabase.createCollection("airship")
    }
}