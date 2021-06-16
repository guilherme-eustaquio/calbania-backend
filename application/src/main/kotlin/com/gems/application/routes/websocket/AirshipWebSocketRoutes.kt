package com.gems.application.routes.websocket

import com.gems.application.context.AirshipWebSocketContext
import com.gems.application.context.CalbaniaWebSocketContext
import com.gems.core.domain.Airship
import com.gems.application.service.AirshipService
import com.gems.core.enum.MessageType
import io.javalin.Javalin
import io.javalin.Javalin.log

fun beginAirshipWebSocketRoutes(app : Javalin) {

    app.ws("/airships") { ws ->
        ws.onConnect { ctx ->
            log.info("${ctx.sessionId} joined the Airship")
        }
        ws.onMessage { ctx ->
            val airship : Airship = ctx.message(Airship::class.java)
            AirshipService.save(airship)
            AirshipWebSocketContext.save(ctx, airship.id)
            log.info("Received message to Airship from: ${airship.id}.")
            airship.command?.type = MessageType.BROADCAST_TO_CALBANIA_CLIENTS.type
            CalbaniaWebSocketContext.broadcast(airship)
        }
        ws.onClose { ctx ->

            val airship = AirshipService.findById(AirshipWebSocketContext.findByContext(ctx))

            if (airship != null) {
                AirshipWebSocketContext.delete(ctx)
                log.info("${airship.id} left the Airship")
                log.info("${airship.id} notifying to the Calbania...")
                airship.command?.type = MessageType.BROADCAST_TO_CALBANIA_CLIENTS.type
                CalbaniaWebSocketContext.broadcast(airship)
            }
        }
    }
}