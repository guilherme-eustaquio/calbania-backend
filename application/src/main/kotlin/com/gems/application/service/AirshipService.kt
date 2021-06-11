package com.gems.application.service

import com.gems.application.context.WebSocketContext
import com.gems.core.domain.Airship
import com.gems.application.repository.AirshipRepository
import com.gems.application.utils.DateUtils.now
import java.util.concurrent.ConcurrentHashMap

object AirshipService {

    private val airshipRepository = AirshipRepository()

    fun save(airship: Airship): Airship? {
        airship.command.timestamp = now()
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
            save(airship)
            airshipContext.send(airship)
        }
    }
}