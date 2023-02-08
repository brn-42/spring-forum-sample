package com.example.forum.service

import com.example.forum.dto.TopicRequestDTO
import com.example.forum.dto.TopicRequestUpdateDTO
import com.example.forum.dto.TopicResponseDTO
import com.example.forum.mapper.toModel
import com.example.forum.mapper.toResponseDTO
import com.example.forum.repository.TopicRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val courseService: CourseService,
    private val userService: UserService
) {

    fun list(): List<TopicResponseDTO> = topicRepository.findAll().map { it.toResponseDTO() }

    fun getById(id: Long): Optional<TopicResponseDTO> = topicRepository.findById(id)?.map { it.toResponseDTO() }

    fun add(topicRequestDTO: TopicRequestDTO): TopicResponseDTO = topicRepository.save(
        topicRequestDTO.toModel(
            course = courseService.getById(topicRequestDTO.courseId).orElseThrow(),
            author = userService.getById(topicRequestDTO.authorId).orElseThrow()
        )
    ).toResponseDTO()

    fun update(topic: TopicRequestUpdateDTO): TopicResponseDTO {
        val topicToUpdate = topicRepository.findById(topic.id).orElseThrow()
        topicToUpdate.title = topic.title
        topicToUpdate.message = topic.message
        return topicToUpdate.toResponseDTO()
    }

    fun delete(id: Long) = topicRepository.deleteById(id)
}