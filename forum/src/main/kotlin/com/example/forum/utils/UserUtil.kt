package com.example.forum.utils

import com.example.forum.model.User

class UserUtil {
    fun users(): List<User> {
        val user1 = User(
            id = 1,
            name = "Joao",
            email = "joao@email"
        )

        val user2 = User(
            id = 2,
            name = "Lindomar",
            email = "joao@email"
        )

        val user3 = User(
            id = 3,
            name = "Jorginho",
            email = "joao@email"
        )

        return listOf(user1, user2, user3)
    }
}