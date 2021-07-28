package com.gems.application.config

import com.gems.application.utils.JsonUtils.toJsonObject
import com.gems.application.utils.JsonUtils.toJsonString
import redis.clients.jedis.Jedis

object DatabaseManager {

    private val connection = Jedis("localhost", 6379)

    fun save(keyName : String, document : Any) {
        connection.lpush(keyName, toJsonString(document))
    }

    fun delete(keyName: String, document: Any, occurrence : Long = 1) {
        connection.lrem(keyName, occurrence, toJsonString(document))
    }

    fun <T> findByKeyName(keyName: String, valueType : Class<T>, current : Long = 0, limit : Long = -1): ArrayList<T> {

        var result = ArrayList<T>()

        connection.lrange(keyName, current, limit).forEach {
            result.add(toJsonObject(it, valueType))
        }

        return result
    }
}
