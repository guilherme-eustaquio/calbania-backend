package com.gems.routes.websocket

import com.gems.context.WebSocketContext
import com.gems.domain.Airship
import com.gems.service.AirshipService
import com.google.gson.Gson
import io.javalin.Javalin
import io.javalin.Javalin.log

fun beginAirshipWebSocketRoutes(app : Javalin) {

    app.ws("/airships") { ws ->
        ws.onConnect { ctx ->
            WebSocketContext.save(ctx)
            log.info("${ctx.sessionId} joined the server")
        }
        ws.onMessage { ctx ->
            val gson = Gson()
            val airship : Airship = gson.fromJson(ctx.message(), Airship::class.java)
            AirshipService.save(airship)
            WebSocketContext.save(ctx, airship.id)
            log.info("receiving message from: ${airship.id}")
        }
        ws.onClose { ctx ->

            var airship = AirshipService.findById(WebSocketContext.findByContext(ctx))

            if (airship != null) {
                airship.status = 0
                WebSocketContext.delete(ctx)
                log.info("${airship.id} left the server")
            }
        }
    }
}