import io.memorix.user.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserRepositoryTest {

    private lateinit var userRepository: UserRepository
    private lateinit var database: Database

    @BeforeEach
    fun setup() {
        database = mockk(relaxed = true)
        userRepository = UserRepository(database)
    }

    @Test
    fun `searchUsers returns users when users exist`() {
        val users = listOf(User("test1@test.com", "Test1", ""), User("test2@test.com", "Test2", ""))
        every { transaction(database) { userRepository.searchUsers("Test", 2) } } returns users

        val result = userRepository.searchUsers("Test", 2)

        assertEquals(users, result)
    }

    @Test
    fun `searchUsers returns empty list when no users exist`() {
        every { transaction(database) { userRepository.searchUsers("Test", 2) } } returns emptyList()

        val result = userRepository.searchUsers("Test", 2)

        assertEquals(emptyList<User>(), result)
    }

    @Test
    fun `addUser inserts user into database`() {
        val user = User("test@test.com", "Test", "password")

        userRepository.addUser(user)

        verify { transaction(database) { userRepository.addUser(user) } }
    }
}