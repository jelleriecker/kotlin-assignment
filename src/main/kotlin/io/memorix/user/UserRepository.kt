package io.memorix.user

import io.vertx.jdbcclient.JDBCPool
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt



class UserRepository<User>(
    val client: JDBCPool
) {
    // Add your repository methods here
    fun create(user: User): Boolean = transaction {
        try {
            Users.insert {
                it[name] = user.name
                it[email] = user.email
                it[passwordHash] = BCrypt.hashpw(user.password, BCrypt.gensalt())

            }
            true
        } catch (e: ExposedSQLException) {
            false
        }
    }

    fun find(email: String): User? = transaction {
        Users.select { Users.email eq email }
            .map { Users.toUser(it) }
            .singleOrNull()
    }

    private fun toUser(row: ResultRow): User = User(
        name = row[Users.name],
        email = row[Users.email],
        password = row[Users.passwordHash]
    )
}
