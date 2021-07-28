package com.gems.application.config

import java.lang.System.setProperty

object JvmConfig {
    fun initJvmConfig() {
        timeZoneConfig()
    }
    private fun timeZoneConfig() {
        setProperty("user.timezone", "America/Sao_Paulo")
    }
}