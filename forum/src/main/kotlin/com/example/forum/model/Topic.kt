package com.example.forum.model

import java.time.LocalDateTime

data class Topic(
    val id: Long? = null,
    val title: String,
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: Status = Status.UNANSWERED,
    val responses: List<Response> = ArrayList()
)

