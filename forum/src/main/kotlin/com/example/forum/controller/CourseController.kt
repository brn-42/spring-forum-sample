package com.example.forum.controller

import com.example.forum.dto.request.CourseRequestDTO
import com.example.forum.dto.response.CourseResponseDTO
import com.example.forum.mapper.toModel
import com.example.forum.mapper.toResponseDTO
import com.example.forum.service.CourseService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/courses")
class CourseController(private val courseService: CourseService) {

    @GetMapping
    fun list(): List<CourseResponseDTO> = courseService.list().map { it.toResponseDTO() }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<CourseResponseDTO> =
        courseService.getById(id).map {
            ResponseEntity.ok(it.toResponseDTO())
        }.orElse(ResponseEntity.notFound().build())

    @PostMapping
    @Transactional
    fun add(
        @RequestBody
        @Valid course: CourseRequestDTO,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<CourseResponseDTO> {
        val topicResponse = courseService.add(course.toModel()).toResponseDTO()
        val uri = uriComponentsBuilder.path("/courses/${topicResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicResponse)
    }
}