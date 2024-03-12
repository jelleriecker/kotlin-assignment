package io.memorix.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.user() {
//    val repository: UserRepository by inject()

    // Add your routes here

    get("/list") {
        try {
            val users = getAllUsers()
            call.respond(users)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e.localizedMessage)
        }
    }
    post("/adduser") {
        try {
            val user = call.receive<User>()
            addUser(user)
            call.respond(HttpStatusCode.Created)
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, e.localizedMessage)
        }
    }
}
