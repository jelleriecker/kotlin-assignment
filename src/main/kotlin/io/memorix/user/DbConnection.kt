package io.memorix.user

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object Users : IntIdTable() {
    val email = text("email").uniqueIndex()
    val name = text("name")
    val password_hash = text("password_hash")
}


fun initDatabase() {
//    val dotenv = Dotenv.configure().load() //TODO FIX .env references

    Database.connect(
        url = "jdbc:postgresql://localhost:5432/ktor",
        driver = "org.postgresql.Driver",
        user = "jelle",
        password = "riecker",
//        url = dotenv["DB_URL"],
//        driver = dotenv["DB_DRIVER"],
//        user = dotenv["DB_USER"],
//        password = dotenv["DB_PASS"],
    )
    transaction {
        // Create tables if they don't exist
        SchemaUtils.create(UsersTables.Users)
    }
}