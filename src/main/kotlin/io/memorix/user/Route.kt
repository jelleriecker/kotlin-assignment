package io.memorix.user

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.jetbrains.exposed.dao.id.IntIdTable


fun Route.user() {
    val repository: UserRepository<Any?> by inject()

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
    get("/user/{email}") {
        val email = call.parameters["email"] ?: return@get call.respond("Missing or malformed email")
        val user = repository.find(email)
        if (user != null) {
            call.respond(user)
        } else {
            call.respond("User not found")
        }
    }
}
