package com.gems.config

import io.javalin.Javalin.log
import redis.clients.jedis.HostAndPort
import redis.clients.jedis.Jedis

class RedisDatabaseConfig {
    companion object {

        private const val host : String = "localhost"
        private const val port : Int = 6379
        private val connection : Jedis = Jedis(HostAndPort(host, port))

        fun getDatabaseSource(localhost : String = "localhost", port : Int = 6379): Jedis {
            return connection
        }
    }
}