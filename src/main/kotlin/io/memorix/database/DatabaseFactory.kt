package io.memorix.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init() {
        Database.connect(
            url = "jdbc:postgresql://${System.getenv("DB_HOST")}:${System.getenv("DB_PORT")}/${System.getenv("DB_NAME")}",
            driver = "org.postgresql.Driver",
            user = System.getenv("DB_USER"),
            password = System.getenv("DB_PASS")
        )
    }

    fun <T> dbQuery(block: () -> T): T =
        transaction { block() }
}