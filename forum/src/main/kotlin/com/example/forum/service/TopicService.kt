package com.example.forum.service

import com.example.forum.dto.TopicRequestDTO
import com.example.forum.dto.TopicRequestUpdateDTO
import com.example.forum.dto.TopicResponseDTO
import com.example.forum.mapper.toModel
import com.example.forum.mapper.toResponseDTO
import com.example.forum.model.Topic
import com.example.forum.utils.TopicUtils
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val courseService: CourseService,
    private val userService: UserService
) {

    private var topics: List<Topic> = TopicUtils().topics()

    fun list(): List<TopicResponseDTO> {
        return topics.map { it.toResponseDTO() }
    }

    fun getById(id: Long): TopicResponseDTO {
        return topics.first { it.id == id }.toResponseDTO()
    }

    fun add(topicRequestDTO: TopicRequestDTO): TopicResponseDTO {
        val topic = topicRequestDTO
            .toModel(
                topics.size.toLong() + 1,
                courseService.getById(topicRequestDTO.courseId),
                userService.getById(topicRequestDTO.authorId)
            )

        topics = topics.plus(topic)
        return topic.toResponseDTO()
    }

    fun update(topic: TopicRequestUpdateDTO): TopicResponseDTO {
        val original = topics.first { it.id == topic.id }
        val new = original.copy(
            title = topic.title,
            message = topic.message,
        )
        topics = topics.minus(original).plus(new)

        return new.toResponseDTO()
    }

    fun delete(id: Long) {
        val original = topics.first { it.id == id }
        topics = topics.minus(original)
    }
}