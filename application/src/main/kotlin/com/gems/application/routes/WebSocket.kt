package com.gems.application.routes

import com.gems.application.routes.websocket.beginAirshipWebSocketRoutes
import com.gems.application.routes.websocket.beginCalbaniaWebSocketRoutes
import io.javalin.Javalin
import io.javalin.Javalin.log

fun mountWebSocketRoutes(app : Javalin) {
    log.info("mounting websockets routes...")
    beginAirshipWebSocketRoutes(app)
    beginCalbaniaWebSocketRoutes(app)
}