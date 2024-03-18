package io.memorix.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.user() {
//    val repository: UserRepository by inject()


    // Add your routes here

    /**
     * GET /users route.
     * This route is currently commented out and needs to be fixed.
     * It is intended to query users based on the provided parameters.
     */
// TODO fix the route to query users.
    get("/users") {
        val query = call.parameters["query"] ?: ""
        val limit = call.parameters["limit"]?.toInt() ?: 10

        val users = searchUsers(query, limit)

        val total = users.size
        val response = mapOf("users" to users, "total" to total)

        // Convert the LinkedHashMap to a serializable map
        val serializableResponse = response.mapValues { it.value.toString() }

        call.respond(serializableResponse)

//        val response = mapOf("users" to users, "total" to total)

//        call.respond(response)
    }














    /**
     * POST /users route.
     * This route is used to add a new user.
     * It expects a User object in the request body.
     */
    post("/users") {
        try {
            val users = call.receive<User>()
            addUser(users)
            call.respond(HttpStatusCode.Accepted, "User added successfully")
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, "Something seems wrong here, did you check for duplicates?")
        }
    }

}


