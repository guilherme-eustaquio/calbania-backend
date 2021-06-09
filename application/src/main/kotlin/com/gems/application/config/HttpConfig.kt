package com.gems.application.config

import com.gems.application.routes.mountHttpRoutes
import com.gems.application.routes.mountWebSocketRoutes
import io.javalin.Javalin

object HttpConfig {

    private const val defaultContentType : String = "application/json"
    private const val webSocketPort : Int = 7070
    private const val httpServerPort : Int = 8080

    fun startWebSocketServer() {
        Javalin.create { config ->
            config.showJavalinBanner = false
            config.enableCorsForAllOrigins()
            config.defaultContentType = defaultContentType
        }.also {
            mountWebSocketRoutes(it)
        }.start(webSocketPort)
    }

    fun startHttpServer() {
        Javalin.create { config ->
            config.showJavalinBanner = false
            config.enableCorsForAllOrigins()
            config.defaultContentType = defaultContentType
        }.also {
            mountHttpRoutes(it)
        }.start(httpServerPort)
    }
}