package com.gems.config

import com.gems.routes.mountHttpRoutes
import com.gems.routes.mountWebSocketRoutes

import io.javalin.Javalin

fun startWebSocketServer(port : Int = 7070) {
    Javalin.create { config ->
        config.showJavalinBanner = false
        config.enableCorsForAllOrigins()
        config.defaultContentType = "application/json"
    }.also {
        mountWebSocketRoutes(it)
    }.start(port)
}

fun startHttpServer(port : Int = 8080) {
    Javalin.create { config ->
        config.showJavalinBanner = false
        config.enableCorsForAllOrigins()
        config.defaultContentType = "application/json"
    }.also {
        mountHttpRoutes(it)
    }.start(port)
}