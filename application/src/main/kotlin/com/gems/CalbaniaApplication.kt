package com.gems

import com.gems.config.startHttpServer
import com.gems.config.startWebSocketServer
import io.javalin.Javalin
import io.javalin.Javalin.log
import io.javalin.websocket.WsContext
import org.json.JSONObject
import java.util.concurrent.ConcurrentHashMap

private val userUsernameMap = ConcurrentHashMap<WsContext, String>()
private var nextUserNumber = 1 // Assign to username for next connecting user

fun main() {

    startWebSocketServer()
    startHttpServer()

    /*
    Javalin.create {
        //it.addStaticFiles("/public")
    }.apply {
        ws("/") { ws ->
            ws.onConnect { ctx ->
                val username = "User" + nextUserNumber++
                userUsernameMap.put(ctx, username)
                broadcastMessage("Server", "$username joined the chat")
                log.info("$username joined the chat")
            }
            ws.onClose { ctx ->
                val username = userUsernameMap[ctx]
                userUsernameMap.remove(ctx)
                broadcastMessage("Server", "$username left the chat")
                log.info("$username left the chat")

            }
            ws.onMessage { ctx ->
                broadcastMessage(userUsernameMap[ctx]!!, ctx.message())
                log.info("receiving message: ${ctx.message()}")
            }

        }
    }.start(7070)*/
}

// Sends a message from one user to all users, along with a list of current usernames
fun broadcastMessage(sender: String, message: String) {
    userUsernameMap.keys.filter { it.session.isOpen }.forEach { session ->
        session.send(
            JSONObject()
                .put("userMessage", message)
                .put("userlist", userUsernameMap.values).toString()
        )
    }
}
