package com.example.forum.mapper

import com.example.forum.dto.request.TopicRequestDTO
import com.example.forum.dto.response.TopicResponseDTO
import com.example.forum.model.Course
import com.example.forum.model.Topic
import com.example.forum.model.ForumUser

fun Topic.toResponseDTO() =
    TopicResponseDTO(
        id = id,
        title = title,
        message = message,
        status = status,
        createdAt = createdAt
    )

fun TopicRequestDTO.toModel(course: Course, author: ForumUser) =
    Topic(
        title = title,
        message = message,
        course = course,
        author = author
    )