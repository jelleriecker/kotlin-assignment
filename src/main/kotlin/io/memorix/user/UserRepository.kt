package io.memorix.user

import io.vertx.jdbcclient.JDBCPool
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt


/**
 * UserRepository class.
 * This class is responsible for handling database operations related to the User entity.
 * It uses the JDBCPool client for database interactions.
 *
 * @property client The JDBCPool client used for database interactions.
 */
class UserRepository(
    val client: JDBCPool
)

    // Add your repository methods here

/**
 * Retrieves users from the database by name.
 * This function uses a transaction to ensure that the operation is atomic.
 * The function returns a list of User objects.
 *
 * @param name The name of the users to be retrieved.
 * @param limit The maximum number of users to be retrieved.
 * @return A list of User objects.
 */

fun searchUsers(name: String, limit: Int): List<User> {
    return transaction {
        val nameParameter = "$name%"
        Users.selectAll().where { Users.name like nameParameter }
            .limit(limit)
            .map { User(it[Users.email], it[Users.name], it[Users.password_hash]) }
    }
}

/**
 * Adds a new user to the database.
 * This function uses a transaction to ensure that the operation is atomic.
 * The password is hashed using BCrypt before being stored in the database.
 *
 * @param user The User object to be added to the database.
 */
fun addUser(user: User) {
    transaction {
        Users.insert {
            it[name] = user.name
            it[email] = user.email
            it[password_hash] = BCrypt.hashpw(user.password_hash, BCrypt.gensalt())
        }
    }
}
