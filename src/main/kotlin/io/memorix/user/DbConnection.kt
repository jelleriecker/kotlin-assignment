package io.memorix.user

import io.github.cdimascio.dotenv.Dotenv

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * GET /users route.
 * This route is currently commented out and needs to be fixed.
 * It is intended to query users based on the provided parameters.
 */
object Users : Table() {
    val email = text("email").uniqueIndex()
    val name = text("name")
    val password_hash = text("password_hash")
}

/**
 * Initializes the database connection and creates the Users table if it doesn't exist.
 * This function uses the Dotenv library to load environment variables from a .env file.
 * The environment variables are used to configure the database connection.
 * The function then connects to the database and creates the Users table if it doesn't exist.
 */
fun initDatabase() {
    val dotenv = Dotenv.configure().load()

    Database.connect(
        url = dotenv["DB_URL"]!!,
        driver = dotenv["DB_DRIVER"]!!,
        user = dotenv["DB_USER"]!!,
        password = dotenv["DB_PASS"]!!
    )

    transaction {
        // Create tables if they don't exist
        SchemaUtils.create(Users)
    }
}