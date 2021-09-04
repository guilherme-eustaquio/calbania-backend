package com.gems.application.utils

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gems.application.response.PageResponse
import io.javalin.http.Context

object JsonUtils {

    private val objectMapper = jacksonObjectMapper()

    init {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun toJsonString(src : Any): String = objectMapper.writeValueAsString(src)

    fun <T> toJsonObject(src : String, valueType : Class<T>): T = objectMapper.readerFor(valueType).readValue(src)

    fun <T> map(src : Any, valueType : Class<T>): T {
        return toJsonObject(toJsonString(src), valueType)
    }

    fun <T> mapAll(src : List<*>, valueType : Class<T>): List<*> {

        val convertedList = ArrayList<T>()

        src.forEach { element ->
            convertedList.add(map(element!!, valueType))
        }

        return toJsonList(toJsonString(convertedList))
    }

    fun paginate(src : List<*>, start : Long, stop : Long): PageResponse {
        return PageResponse(src, start, stop)
    }

    private fun toJsonList(src : String): List<*> = objectMapper.readValue(src, List::class.java)

}