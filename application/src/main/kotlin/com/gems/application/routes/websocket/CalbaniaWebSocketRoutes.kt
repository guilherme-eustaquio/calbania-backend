package com.gems.application.routes.websocket

import com.gems.application.context.CalbaniaWebSocketContext
import com.gems.application.enum.Roles
import com.gems.application.service.AirshipService
import com.gems.core.domain.Airship
import com.gems.core.enum.MessageType
import io.javalin.Javalin
import io.javalin.Javalin.log
import io.javalin.core.security.SecurityUtil.roles

fun beginCalbaniaWebSocketRoutes(app : Javalin) {
    
    app.ws("/calbania", { ws ->
        ws.onConnect { ctx ->

            log.info("${ctx.sessionId} joined the Calbania")

            CalbaniaWebSocketContext.save(ctx)
            val airships = AirshipService.findAll()

            if(!airships.isEmpty()) {

                log.info("Sending to the Calbania client connected the current status of the airships...")

                airships.forEach {
                    it.value.command?.type = MessageType.SEND_MESSAGE_FROM_CALBANIA.type
                }

                ctx.send(airships) // send to client connected the current status of the airship
            }
        }
        ws.onMessage { ctx ->
            log.info("Received message to Calbania.")
            log.info("Sending message to Airship...")
            val airship = ctx.message(Airship::class.java)

            airship.command?.type = MessageType.SEND_MESSAGE_FROM_CALBANIA.type
            AirshipService.send(ctx.message(Airship::class.java))
        }
        ws.onClose { ctx ->
            log.info("${ctx.sessionId} left the Calbania")
            CalbaniaWebSocketContext.remove(ctx)
        }
    }, roles(Roles.USER, Roles.ADMIN))
}