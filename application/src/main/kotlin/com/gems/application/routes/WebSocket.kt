package com.gems.application.routes

import com.gems.application.routes.websocket.beginAirshipWebSocketRoutes
import com.gems.application.routes.websocket.beginCalbaniaWebSocketRoutes
import io.javalin.Javalin

fun mountWebSocketRoutes(app : Javalin) {
    beginAirshipWebSocketRoutes(app)
    beginCalbaniaWebSocketRoutes(app)
}