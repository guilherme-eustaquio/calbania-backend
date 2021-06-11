package com.gems.application.routes.http

import com.gems.application.config.DatabaseManager
import com.gems.application.service.AirshipService
import com.gems.core.domain.Airship
import io.javalin.Javalin

fun beginAirshipHttpRoutes(app : Javalin) {

    val contextPath = "/airships"

    app.get(contextPath) { ctx ->
        AirshipService.findAll()?.let { ctx.json(it) }
    }

    app.get("$contextPath/:id") { ctx ->
        val id = ctx.pathParam("id")
        AirshipService.findById(id)?.let { ctx.json(it) }
    }

    app.post(contextPath) { ctx ->
        val airship = ctx.body<Airship>()
        AirshipService.send(airship)
        ctx.json(airship)
    }

    app.get("$contextPath/logs/:id") { ctx ->
        DatabaseManager.findByKeyName(ctx.pathParam("id")).let { ctx.json(it) }
    }

}