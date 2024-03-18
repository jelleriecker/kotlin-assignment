package io.memorix.user

import kotlinx.serialization.Serializable

/**
 * User data class.
 * This class represents a User in the system.
 * It is marked as Serializable to allow it to be converted to and from JSON.
 * It contains three properties: email, name, and password_hash.
 *
 * @property email The email of the user. It is a unique identifier for the user.
 * @property name The name of the user.
 * @property password_hash The hashed password of the user.
 */
@Serializable
data class User(
    val email: String,
    val name: String,
    val password_hash: String
)

