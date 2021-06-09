package com.gems.application.service

import com.gems.application.context.WebSocketContext
import com.gems.core.domain.Airship
import com.gems.application.repository.AirshipRepository
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

    fun send(airship: Airship) {

        val airshipContext = WebSocketContext.findById(airship.id)

        if(airshipContext != null && airshipContext.session.isOpen) {
            airshipContext.send(airship)
        }
    }
}