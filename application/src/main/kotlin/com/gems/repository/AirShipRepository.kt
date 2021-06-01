package com.gems.repository

import com.gems.config.RedisDatabaseConfig
import com.gems.domain.Airship
import com.google.gson.Gson

class AirShipRepository {

    private val jedis = RedisDatabaseConfig.getDatabaseSource()

    fun save(airship: Airship) {
        val gson = Gson()
        jedis.set("airship:${airship.macAddress}", gson.toJson(airship))
    }

    fun listAll() {
        //jedis.get()

    }

    fun findByMacAddress(macAddress : String) : Airship? {
        val gson = Gson()
        val airshipJson : Set<String> = jedis.smembers("airships")

        for(airship in airshipJson) {

            val airshipConverted : Airship = gson.fromJson(airship, Airship::class.java)

            if(airshipConverted.macAddress == macAddress) {
                return airshipConverted
            }

        }
        return null
    }
}