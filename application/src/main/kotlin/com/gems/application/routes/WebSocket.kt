package com.gems.application.routes

import com.gems.application.routes.websocket.beginAirshipWebSocketRoutes
import io.javalin.Javalin

fun mountWebSocketRoutes(app : Javalin) {
    beginAirshipWebSocketRoutes(app)
}