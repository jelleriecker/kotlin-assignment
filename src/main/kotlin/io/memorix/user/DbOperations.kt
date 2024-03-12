package io.memorix.user


import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt

fun addUser(user: User) {
    transaction {
        Users.insertAndGetId {
//            it[id] = user.id.toInt(+1)
            it[name] = user.name
            it[email] = user.email
            it[password_hash] = BCrypt.hashpw(user.password_hash, BCrypt.gensalt())
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
