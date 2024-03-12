package io.memorix.user


import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun addUser(user: User) {
    transaction {
        Users.insertAndGetId {
            it[name] = user.name
            it[email] = user.email
            it[password_hash] = user.password_hash
        }
    }
}

fun getAllUsers(): List<User> {
    return transaction {
        Users.selectAll().map {
            User(
                it[Users.name],
                it[Users.email],
                it[Users.password_hash]
            )
        }
    }
}