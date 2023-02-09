package com.example.forum.dto.request

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class CourseRequestDTO(
    @field: NotEmpty(message = "Name cant be empty")
    @field: Size(min = 2, max = 100, message = "Name must have between 2 and 100 characters")
    val name: String,

    @field: NotEmpty(message = "Category cant be empty")
    @field: Size(min = 2, max = 100, message = "Category must have between 2 and 100 characters")
    val category: String
)