package com.gems.application.context

import io.javalin.websocket.WsContext
import java.util.concurrent.ConcurrentHashMap

object WebSocketContext {

    private val webSocketContext = ConcurrentHashMap<WsContext, String?>()

    fun save(context : WsContext, id : String = "") {

        val lastContext = findById(id)

        if(lastContext != null && !lastContext.session.isOpen) {
            lastContext.session.disconnect()
            webSocketContext.remove(lastContext)
            return
        }

        webSocketContext[context] = id
    }

    fun findByContext(context : WsContext): String? {
        return webSocketContext[context]
    }

    fun findById(id : String): WsContext ? {

        for ((key, value) in webSocketContext) {
            if(value == id) {
                return key
            }
        }

        return null
    }

    fun findAll(): ConcurrentHashMap<WsContext, String?> {
        return webSocketContext
    }

    fun delete(context : WsContext) {
        webSocketContext.remove(context)
    }
}