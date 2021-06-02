package com.gems.routes.http

import com.gems.service.AirshipService
import io.javalin.Javalin

fun beginAirshipHttpRoutes(app : Javalin) {

    app.get("/airships") { ctx ->
        AirshipService.findAll()?.let { ctx.json(it) }
    }

    app.get("/airships/:id") { ctx ->
        val id = ctx.pathParam("id")
        AirshipService.findById(id)?.let { ctx.json(it) }
    }
}