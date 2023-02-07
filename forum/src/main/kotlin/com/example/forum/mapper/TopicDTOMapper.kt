package com.example.forum.mapper

import com.example.forum.dto.TopicRequestDTO
import com.example.forum.dto.TopicResponseDTO
import com.example.forum.model.Course
import com.example.forum.model.Topic
import com.example.forum.model.User

fun Topic.toResponseDTO() =
    TopicResponseDTO(
        id = id,
        title = title,
        message = message,
        status = status,
        createdAt = createdAt
    )

fun TopicRequestDTO.toModel(id: Long, course: Course, author: User) =
    Topic(
        id = id,
        title = title,
        message = message,
        course = course,
        author = author
    )