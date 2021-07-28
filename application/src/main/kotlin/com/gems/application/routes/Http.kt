package com.gems.application.routes

import com.gems.application.routes.http.beginAirshipHttpRoutes
import com.gems.application.routes.http.beginAuthHttpRoutes
import io.javalin.Javalin
import io.javalin.Javalin.log

fun mountHttpRoutes(app : Javalin) {
    log.info("mounting http routes...")
    beginAuthHttpRoutes(app)
    beginAirshipHttpRoutes(app)
}