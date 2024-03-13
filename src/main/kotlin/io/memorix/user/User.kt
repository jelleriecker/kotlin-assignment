package io.memorix.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val name: String,
    val password_hash: String
)

@Serializable
data class UsersResponse(
    val tablename: String,
    val columns: List<User>
)