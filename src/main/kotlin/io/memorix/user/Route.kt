package io.memorix.user

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.jetbrains.exposed.dao.id.IntIdTable


fun Route.user() {
    val repository: UserRepository by inject()

    data class User(val name: String, val email: String)

    // Add your routes here
    post("/user") {
        val user = call.receive<User>()
        val isCreated = repository.create(user)
        if (isCreated) {
            call.respond("User created")
        } else {
            call.respond("User not created")
        }

    }
    get("/user/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id == null) {
            call.respond("Invalid id")
        } else {
            val user = repository.getById(id)
            if (user == null) {
                call.respond("User not found")
            } else {
                call.respond(user)
            }
        }
    }
}
