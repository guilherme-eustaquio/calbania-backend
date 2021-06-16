package com.gems.application.context

import io.javalin.Javalin.log
import io.javalin.websocket.WsContext
import java.util.concurrent.ConcurrentLinkedQueue

object CalbaniaWebSocketContext {

    private val calbaniaWebSocketContext = ConcurrentLinkedQueue<WsContext>()

    fun save(context: WsContext) {
        calbaniaWebSocketContext.add(context)
    }

    fun broadcast(message : Any) {
        log.info("Broadcasting messages to Calbania clients...")
        calbaniaWebSocketContext.forEach { ws ->
            if(ws.session.isOpen) {
                ws.send(message)
            }
        }
    }

    fun remove(context : WsContext) {
        calbaniaWebSocketContext.remove(context)
    }

}