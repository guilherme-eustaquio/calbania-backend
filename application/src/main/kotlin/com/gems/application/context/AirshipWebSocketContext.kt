package com.gems.application.context

import io.javalin.websocket.WsContext
import java.util.concurrent.ConcurrentHashMap

object AirshipWebSocketContext {

    private val airshipWebSocketContext = ConcurrentHashMap<WsContext, String?>()

    fun save(context : WsContext, id : String = "") {

        val lastContext = findById(id)

        if(lastContext != null && !lastContext.session.isOpen) {
            airshipWebSocketContext.remove(lastContext)
            return
        }

        airshipWebSocketContext[context] = id
    }

    fun findByContext(context : WsContext): String? {
        return airshipWebSocketContext[context]
    }

    fun findById(id : String): WsContext ? {

        for ((key, value) in airshipWebSocketContext) {
            if(value == id) {
                return key
            }
        }

        return null
    }

    fun findAll(): ConcurrentHashMap<WsContext, String?> {
        return airshipWebSocketContext
    }

    fun delete(context : WsContext) {
        airshipWebSocketContext.remove(context)
    }
}