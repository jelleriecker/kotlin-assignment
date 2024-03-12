package io.memorix.user

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

//val DB_NAME: String = System.getenv("DB_NAME")
//val DB_USER: String = System.getenv("DB_USER")
//val DB_PASS: String = System.getenv("DB_PASS")
//val DB_URL: String = System.getenv("DB_URL")
//val DB_DRIVER: String = System.getenv("DB_DRIVER")

//object Users : IntIdTable() {
//    val name = varchar("name", 50)
//    val email = varchar("email", 50).uniqueIndex()
//    val password_hash = varchar("password", 50)
//}

object Users : IntIdTable() {
    val name = varchar("name", 50)
    val email = varchar("email", 50).uniqueIndex()
    val password_hash = varchar("password", 50)
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