package io.memorix.user

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction



object Users : IntIdTable() {
    val name = text("name")
    val email = text("email").uniqueIndex()
    val password_hash = text("password_hash")
}


fun initDatabase() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/ktor",
        driver = "org.postgresql.Driver",
        user = "jelle",
        password = "riecker"
    )
    transaction {
        // Create tables if they don't exist
        SchemaUtils.create(UsersTables.Users)
    }
}