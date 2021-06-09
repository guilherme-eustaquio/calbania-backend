package com.gems.application.routes

import com.gems.application.routes.http.beginAirshipHttpRoutes
import io.javalin.Javalin

fun mountHttpRoutes(app : Javalin) {
    beginAirshipHttpRoutes(app)
}