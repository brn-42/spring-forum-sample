package com.example.forum.service

import com.example.forum.dto.request.TopicRequestDTO
import com.example.forum.dto.request.TopicRequestUpdateDTO
import com.example.forum.mapper.toModel
import com.example.forum.model.Topic
import com.example.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val courseService: CourseService,
    private val userService: UserService
) {

    fun list(courseName: String?, pageable: Pageable): Page<Topic> =
        courseName?.let {
            topicRepository.findByCourseName(courseName, pageable)
        } ?: let {
            topicRepository.findAll(pageable)
        }

    fun getById(id: Long): Optional<Topic> = topicRepository.findById(id)

    fun add(topicRequestDTO: TopicRequestDTO): Topic = topicRepository.save(
        topicRequestDTO.toModel(
            course = courseService.getById(topicRequestDTO.courseId).orElseThrow(),
            author = userService.getById(topicRequestDTO.authorId).orElseThrow()
        )
    )

    fun update(topic: TopicRequestUpdateDTO): Topic {
        val topicToUpdate = topicRepository.findById(topic.id).orElseThrow()
        topicToUpdate.title = topic.title
        topicToUpdate.message = topic.message
        return topicToUpdate
    }

    fun delete(id: Long) = topicRepository.deleteById(id)
}