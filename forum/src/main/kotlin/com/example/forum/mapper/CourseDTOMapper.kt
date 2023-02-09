package com.example.forum.mapper

import com.example.forum.dto.request.CourseRequestDTO
import com.example.forum.dto.response.CourseResponseDTO
import com.example.forum.model.Course

fun Course.toResponseDTO() =
    CourseResponseDTO(
        id = id,
        name = name,
        category = category
    )

fun CourseRequestDTO.toModel() =
    Course(
        name = name,
        category = category
    )