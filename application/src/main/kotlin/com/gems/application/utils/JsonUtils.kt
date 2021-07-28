package com.gems.application.utils

import com.fasterxml.jackson.databind.ObjectMapper

object JsonUtils {

    private val objectMapper = ObjectMapper()

    fun toJsonString(src : Any): String = objectMapper.writeValueAsString(src)

    fun <T> toJsonObject(src : String, valueType : Class<T>): T = objectMapper.readValue(src, valueType)

    fun toJsonList(src : String): List<*> = objectMapper.readValue(src, List::class.java)

}