package io.memorix.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.memorix.user.user

fun Application.configureRouting() {
    install(AutoHeadResponse)
    install(Resources)
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
            call.respondText(text = "404: $cause", status = HttpStatusCode.NotFound)
            call.respondText(text = "202: $cause", status = HttpStatusCode.Accepted)
            call.respondText(text = "400: $cause", status = HttpStatusCode.BadRequest)
        }
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        user()
    }
}
