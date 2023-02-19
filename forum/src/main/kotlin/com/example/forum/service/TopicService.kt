package com.example.forum.service

import com.example.forum.dto.request.TopicRequestDTO
import com.example.forum.dto.request.TopicRequestUpdateDTO
import com.example.forum.mapper.toModel
import com.example.forum.model.Topic
import com.example.forum.repository.TopicRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
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

    @Cacheable("findAllTopics")
    fun list(courseName: String?, pageable: Pageable): Page<Topic> =
        courseName?.let {
            topicRepository.findByCourseName(courseName, pageable)
        } ?: let {
            topicRepository.findAll(pageable)
        }

    @Cacheable("getById")
    fun getById(id: Long): Optional<Topic> = topicRepository.findById(id)

    @CacheEvict(
        value = ["topicsByCourseName", "findAllTopics", "getById"],
        allEntries = true
    )
    fun add(topicRequestDTO: TopicRequestDTO): Topic = topicRepository.save(
        topicRequestDTO.toModel(
            course = courseService.getById(topicRequestDTO.courseId).orElseThrow(),
            author = userService.getById(topicRequestDTO.authorId).orElseThrow()
        )
    )

    @CacheEvict(
        value = ["topicsByCourseName", "findAllTopics", "getById"],
        allEntries = true
    )
    fun update(topic: TopicRequestUpdateDTO): Topic {
        val topicToUpdate = topicRepository.findById(topic.id).orElseThrow()
        topicToUpdate.title = topic.title
        topicToUpdate.message = topic.message
        return topicToUpdate
    }

    @CacheEvict(
        value = ["topicsByCourseName", "findAllTopics", "getById"],
        allEntries = true
    )
    fun delete(id: Long) = topicRepository.deleteById(id)
}