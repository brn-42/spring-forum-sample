package com.example.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicRequestDTO(
    @field: NotEmpty(message = "Title cant be empty")
    @field: Size(min = 5, max = 100, message = "Title must have between 5 and 100 characters")
    val title: String,

    @field: NotEmpty(message = "Message cant be empty")
    val message: String,

    @field: NotNull(message = "Course ID cant be null")
    val courseId: Long,

    @field: NotNull(message = "Author ID cant be null")
    val authorId: Long
)