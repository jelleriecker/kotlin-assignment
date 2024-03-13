package io.memorix.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.user() {
//    val repository: UserRepository by inject()

    // Add your routes here

    get("/users") {
        try {
            val users = getAllUsers()
            val response = UsersResponse(
                tablename = "users",
                columns = users
            )
            call.respond(HttpStatusCode.Found, "here should be a list with all users from DB")
            call.respond(response)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError, e.localizedMessage)
        }
    }


    post("/users") {
//        TODO("post requests return 405's, need to fix this.")
        try {
            val users = call.receive<User>()
            addUser(users)
            call.respond(HttpStatusCode.Accepted, "User added successfully")
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, "Something seems wrong here, did you check for duplicates?")
        }
    }
}


