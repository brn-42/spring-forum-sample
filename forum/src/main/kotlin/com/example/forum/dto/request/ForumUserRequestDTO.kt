package com.example.forum.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class ForumUserRequestDTO(
    @field: NotEmpty(message = "Name cant be empty")
    @field: Size(min = 2, max = 100, message = "Name must have between 2 and 100 characters")
    val name: String,

    @field: NotEmpty(message = "email cant be empty")
    @field: Size(min = 2, max = 100, message = "email must have between 2 and 100 characters")
    val email: String,

    @field: NotEmpty(message = "password cant be empty")
    @field: Size(min = 3, max = 100, message = "password must have between 3 and 100 characters")
    val password: String,
)