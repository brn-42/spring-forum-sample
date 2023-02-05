package com.example.forum.dto

data class TopicDTO(
    val title: String,
    val message: String,
    val courseId: Long,
    val authorId: Long
)

fun TopicDTO.toModel() {

}