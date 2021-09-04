package com.gems.application.config

import com.gems.application.utils.JsonUtils.toJsonObject
import com.gems.application.utils.JsonUtils.toJsonString
import io.javalin.Javalin.log
import redis.clients.jedis.Jedis
import redis.clients.jedis.Response
import redis.clients.jedis.Transaction
import redis.clients.jedis.exceptions.JedisException
import kotlin.collections.ArrayList
import kotlin.collections.forEach


object DatabaseManager {

    private val connection = Jedis("localhost", 6379)

    fun save(keyName : String, document : Any, transactional : Transaction? = null) {

        if(transactional != null) {
            transactional.lpush(keyName, toJsonString(document))
            return
        }

        connection.lpush(keyName, toJsonString(document))
    }

    fun delete(keyName: String, document: Any, occurrence: Long = 1, transactional: Transaction? = null) {

        if(transactional != null) {
            transactional.lrem(keyName, occurrence, toJsonString(document))
            return
        }

        connection.lrem(keyName, occurrence, toJsonString(document))
    }

    fun <T> findByKeyName(keyName: String, valueType : Class<T>, start : Long = 0, stop : Long = -1): ArrayList<T> {

        val result = ArrayList<T>()

        connection.lrange(keyName, start, stop).forEach {
            result.add(toJsonObject(it, valueType))
        }

        return result
    }

    fun transaction(execution : (transactional : Transaction) -> Unit) : MutableList<Response<*>>? {
        val transactionConnection : Transaction = connection.multi()
        try {
            execution(transactionConnection)
        } catch(jedisException : JedisException) {
            log.error("error on transaction", jedisException)
            transactionConnection.discard()
            throw Exception()
        }

        val result = transactionConnection.execGetResponse()
        connection.resetState()
        return result
    }

}
