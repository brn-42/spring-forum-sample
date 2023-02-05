package com.example.forum.utils

import com.example.forum.model.Course
import com.example.forum.model.Topic
import com.example.forum.model.User

class TopicUtils {
    fun topics(): List<Topic> {
        val topic1 = Topic(
            id = 1,
            title = "kotlin",
            message = "val or var",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Spring"
            ),
            author = User(
                id = 1,
                name = "Joao",
                email = "joao@email"
            )
        )

        val topic2 = Topic(
            id = 2,
            title = "kotlin",
            message = "val or var",
            course = Course(
                id = 1,
                name = "Kotlin",
                category = "Spring"
            ),
            author = User(
                id = 1,
                name = "Joao",
                email = "joao@email"
            )
        )

        val topic3 = Topic(
            id = 3,
            title = "kotlin",
            message = "val or var",
            course = Course(
                id = 2,
                name = "Kotlin",
                category = "Spring"
            ),
            author = User(
                id = 1,
                name = "Joao",
                email = "joao@email"
            )
        )

        return listOf(topic1, topic2, topic3)
    }
}