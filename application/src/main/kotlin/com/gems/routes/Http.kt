package com.gems.routes

import com.gems.routes.http.beginAirshipHttpRoutes
import io.javalin.Javalin

fun mountHttpRoutes(app : Javalin) {
    beginAirshipHttpRoutes(app)
}