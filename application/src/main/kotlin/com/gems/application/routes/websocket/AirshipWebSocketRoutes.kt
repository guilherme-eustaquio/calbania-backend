package com.gems.application.routes.websocket

import com.gems.application.context.WebSocketContext
import com.gems.core.domain.Airship
import com.gems.application.service.AirshipService
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
            val airship : Airship = Gson().fromJson(ctx.message(), Airship::class.java)
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