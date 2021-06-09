package com.gems.application.config

import com.gems.application.util.DatabaseCredentials.databaseName
import com.github.cloudyrock.standalone.MongockStandalone
import com.gems.application.util.DatabaseCredentials.uri
import com.github.cloudyrock.mongock.driver.mongodb.sync.v4.driver.MongoSync4Driver
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients


object MigrationsConfig {

    private val connection : MongoClient = MongoClients.create(uri)

    fun initMigrations() {

        MongockStandalone.builder()
            .setDriver(
                MongoSync4Driver.withDefaultLock(connection, databaseName))
                .addChangeLogsScanPackage("com.gems.application.changelog")
                .buildRunner()
    }
}