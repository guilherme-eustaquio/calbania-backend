package com.gems.routes

import io.javalin.Javalin
import io.javalin.Javalin.log

fun mountWebSocketRoutes(app : Javalin) {
    app.ws("/") { ws ->
        ws.onConnect { ctx ->
            //val username = "User" + nextUserNumber++
            //userUsernameMap.put(ctx, username)
            //broadcastMessage("Server", "$username joined the chat")
            log.info("someone joined the chat")
        }
    }
}