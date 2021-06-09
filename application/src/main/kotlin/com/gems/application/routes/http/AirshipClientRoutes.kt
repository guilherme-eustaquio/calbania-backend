package com.gems.application.routes.http

import com.gems.application.service.AirshipService
import com.gems.core.domain.Airship
import com.google.gson.Gson
import io.javalin.Javalin

fun beginAirshipHttpRoutes(app : Javalin) {

    val contextPath = "/airships"

    app.get("$contextPath") { ctx ->
        AirshipService.findAll()?.let { ctx.json(it) }
    }

    app.get("$contextPath/:id") { ctx ->
        val id = ctx.pathParam("id")
        AirshipService.findById(id)?.let { ctx.json(it) }
    }

    app.post("$contextPath") { ctx ->
        val airship = Gson().fromJson(ctx.body(), Airship::class.java)
        AirshipService.send(airship)
        ctx.json(airship)
    }
}