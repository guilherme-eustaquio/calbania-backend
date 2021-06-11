package com.gems.application.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter




object DateUtils {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")

    fun now(): String = LocalDateTime.now().format(formatter)

}