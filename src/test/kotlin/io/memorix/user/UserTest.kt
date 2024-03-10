package io.memorix.user

import io.ktor.client.request.*
import io.ktor.server.testing.*
import io.memorix.module
import kotlin.test.Test

class UserTest {

    @Test
    fun testPostUser() = testApplication {
        application {
            module()
        }
        client.post("/user").apply {
            TODO("Please write your test here")
        }
    }

    @Test
    fun testGetUserId() = testApplication {
        application {
            module()
        }
        client.get("/user/{id}").apply {
            TODO("Please write your test here")
        }
    }
}