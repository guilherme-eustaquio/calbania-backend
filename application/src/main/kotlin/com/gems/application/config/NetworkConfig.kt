package com.gems.application.config

import com.gems.application.config.AccessManagerConfig.setJwtAccessManager
import com.gems.application.config.JvmConfig.initJvmConfig
import com.gems.application.handler.mountHandlerExceptions
import com.gems.application.routes.mountHttpRoutes
import com.gems.application.routes.mountWebSocketRoutes
import com.gems.application.service.UserService
import io.javalin.Javalin

object NetworkConfig {

    private const val defaultContentType : String = "application/json"
    private const val port : Int = 7070

    fun startServer() {
        Javalin.create { config ->
            config.showJavalinBanner = false
            config.enableCorsForAllOrigins()
            config.defaultContentType = defaultContentType
            config.contextPath = "/v1"
        }.also {
            initJvmConfig()
            setJwtAccessManager(it)
            UserService.createAdminUser()
            mountHandlerExceptions(it)
            mountWebSocketRoutes(it)
            mountHttpRoutes(it)
        }.start(port)
    }
}