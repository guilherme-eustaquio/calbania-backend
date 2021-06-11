package com.gems.application

import com.gems.application.config.NetworkConfig.startHttpServer
import com.gems.application.config.NetworkConfig.startWebSocketServer

fun main() {
    startWebSocketServer()
    startHttpServer()
}
