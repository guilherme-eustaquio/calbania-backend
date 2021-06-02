package com.gems.routes

import com.gems.routes.websocket.beginAirshipWebSocketRoutes
import io.javalin.Javalin

fun mountWebSocketRoutes(app : Javalin) {
    beginAirshipWebSocketRoutes(app)
}