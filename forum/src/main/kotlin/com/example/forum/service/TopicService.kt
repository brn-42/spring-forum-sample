package com.example.forum.service

import com.example.forum.dto.TopicDTO
import com.example.forum.model.Topic
import com.example.forum.utils.TopicUtils
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val courseService: CourseService,
    private val userService: UserService
) {

    private var topics: List<Topic> = TopicUtils().topics()

    fun list(): List<Topic> {
        return topics
    }

    fun getById(id: Long): Topic {
        return topics.first { it.id == id }
    }

    fun add(topicDTO: TopicDTO) {
        topics = topics.plus(
            Topic(
                id = topics.size.toLong() + 1,
                title = topicDTO.title,
                message = topicDTO.message,
                course = courseService.getById(topicDTO.courseId),
                author = userService.getById(topicDTO.authorId)
            )
        )
    }
}