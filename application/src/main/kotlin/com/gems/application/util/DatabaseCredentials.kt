package com.gems.application.util

object DatabaseCredentials {
    private const val username = "calbania"
    private const val password = "BAYDAaXPGeeCRnw"
    private const val host = "localhost"
    private const val port = "27017"
    const val databaseName = "CalbaniaDatabase"
    const val uri = "mongodb://${username}:${password}@${host}:${port}/?authSource=${databaseName}"
}