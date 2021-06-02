package com.gems.service

import com.gems.context.WebSocketContext
import com.gems.domain.Airship
import com.gems.repository.AirshipRepository
import java.util.concurrent.ConcurrentHashMap

object AirshipService {

    private val airshipRepository = AirshipRepository()

    fun save(airship: Airship): Airship? {
        return airshipRepository.save(airship)
    }

    fun findAll() : ConcurrentHashMap<String, Airship>? {
        return airshipRepository.findAll()
    }

    fun findById(id : String?) : Airship? {
        return airshipRepository.findById(id)
    }

    fun send(id : String) {

        val airshipContext = WebSocketContext.findById(id)

        if(airshipContext != null && airshipContext.session.isOpen) {
            airshipContext.send("Hello world");
        }
    }
}