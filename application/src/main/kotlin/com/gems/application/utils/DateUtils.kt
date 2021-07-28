package com.gems.application.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")

    fun now(): String = LocalDateTime.now().format(formatter)
    fun plusYear(year : Long = 1) : Date = Date.from(LocalDateTime.now().plusYears(year).atZone(ZoneId.systemDefault()).toInstant())
}