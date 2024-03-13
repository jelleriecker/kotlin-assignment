package io.memorix.user

import org.jetbrains.exposed.sql.Table

class UsersTables {
    object Users : Table() {
        val email = varchar("email", 255).uniqueIndex()
        val name = varchar("name", 255)
        val password_hash = varchar("password_hash", 255)
    }
}

