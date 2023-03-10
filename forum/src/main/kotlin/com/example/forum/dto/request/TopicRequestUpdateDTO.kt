package com.example.forum.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicRequestUpdateDTO(
    @field: NotNull(message = "ID cant be null")
    val id: Long,

    @field: NotEmpty(message = "Title cant be empty")
    @field: Size(min = 5, max = 100, message = "Title must have between 5 and 100 characters")
    val title: String,

    @field: NotEmpty(message = "Message cant be empty")
    val message: String
)