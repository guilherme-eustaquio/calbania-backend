package com.gems.application

import com.gems.application.config.HttpConfig.startHttpServer
import com.gems.application.config.HttpConfig.startWebSocketServer
import com.gems.application.config.MigrationsConfig.initMigrations

fun main() {
    initMigrations()
    startWebSocketServer()
    startHttpServer()
}
