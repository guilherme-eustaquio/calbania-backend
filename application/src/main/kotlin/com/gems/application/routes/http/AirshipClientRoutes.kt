package com.gems.application.routes.http

import com.gems.application.config.DatabaseManager
import com.gems.application.enum.Roles
import com.gems.application.service.AirshipService
import com.gems.core.domain.Airship
import io.javalin.Javalin
import io.javalin.core.security.SecurityUtil.roles

fun beginAirshipHttpRoutes(app : Javalin) {

    val contextPath = "/airships"

    app.get(contextPath) { ctx ->
        AirshipService.findAll().let { ctx.json(it) }
    }

    app.get("$contextPath/:id", { ctx ->
        val id = ctx.pathParam("id")
        AirshipService.findById(id)?.let { ctx.json(it) }
    }, roles(Roles.ADMIN, Roles.USER))

    app.post(contextPath) { ctx ->
        val airship = ctx.body<Airship>()
        ctx.json(airship)
    }

    app.get("$contextPath/logs/:id", { ctx ->
        DatabaseManager.findByKeyName(ctx.pathParam("id"), Airship::class.java, 0, 10).let { ctx.json(it) }
    }, roles(Roles.ADMIN, Roles.USER))

}