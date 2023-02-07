package com.example.forum.dto

import com.example.forum.model.Status
import java.time.LocalDateTime

data class TopicResponseDTO(
    val id: Long?,
    val title: String,
    val message: String,
    val status: Status,
    val createdAt: LocalDateTime
)