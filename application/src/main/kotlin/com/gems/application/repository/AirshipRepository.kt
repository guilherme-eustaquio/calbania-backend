package com.gems.application.repository

import com.gems.core.domain.Airship
import java.util.concurrent.ConcurrentHashMap

class AirshipRepository {

    private val airshipStorage = ConcurrentHashMap<String, Airship>()

    fun save(airship: Airship): Airship? {
        LogRepository.save(airship)
        airshipStorage[airship.id] = airship
        return airshipStorage[airship.id]
    }

    fun findAll() : ConcurrentHashMap<String, Airship> {
        return airshipStorage
    }

    fun findById(id : String?) : Airship? {
        return airshipStorage[id]
    }
}