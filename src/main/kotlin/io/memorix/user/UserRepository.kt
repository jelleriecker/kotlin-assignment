package io.memorix.user

import io.vertx.jdbcclient.JDBCPool
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt


class UserRepository(
    val client: JDBCPool
) {
    // Add your repository methods here
    fun create(user: User): Boolean {
        return transaction {
            try {
                Users.insert {
                    it[email] = user.email
                    it[name] = user.name
                    it[passwordHash] = BCrypt.hashpw(user.password, BCrypt.gensalt())
                }
                true
            } catch (e: ExposedSQLException) {
                false
            }
        }
    }
}
