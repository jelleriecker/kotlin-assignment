package io.memorix.user

data class Users(val name: String, val email: String, val password: String) {
    companion object {
        fun insert(function: () -> Unit) {

        }
    }
}