package com.gems

import com.gems.config.startHttpServer
import com.gems.config.startWebSocketServer

fun main() {
    startWebSocketServer()
    startHttpServer()
}
