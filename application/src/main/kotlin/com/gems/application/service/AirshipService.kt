package com.gems.application.service

import com.gems.application.context.AirshipWebSocketContext
import com.gems.core.domain.Airship
import com.gems.application.repository.AirshipRepository
import com.gems.application.utils.DateUtils.now
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

object AirshipService {

    private val airshipRepository = AirshipRepository()

    fun save(airship: Airship): Airship? {
        airship.command?.id = UUID.randomUUID().toString()
        airship.command?.timestamp = now()
        return airshipRepository.save(airship)
    }

    fun findAll() : ConcurrentHashMap<String, Airship> {
        return airshipRepository.findAll()
    }

    fun findById(id : String?) : Airship? {
        return airshipRepository.findById(id)
    }

    fun send(airship: Airship) {

        val airshipContext = AirshipWebSocketContext.findById(airship.id)

        if(airship.command != null && airshipContext != null && airshipContext.session.isOpen) {
            save(airship)
            airshipContext.send(airship)
        }
    }
}