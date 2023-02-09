package com.example.forum.controller

import com.example.forum.dto.request.TopicRequestDTO
import com.example.forum.dto.request.TopicRequestUpdateDTO
import com.example.forum.dto.response.TopicResponseDTO
import com.example.forum.mapper.toResponseDTO
import com.example.forum.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topics")
class TopicController(private val topicService: TopicService) {

    @GetMapping
    fun list(): List<TopicResponseDTO> {
        return topicService.list().map { it.toResponseDTO() }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<TopicResponseDTO> =
        topicService.getById(id).map {
            ResponseEntity.ok(it.toResponseDTO())
        }.orElse(ResponseEntity.notFound().build())

    @PostMapping
    @Transactional
    fun add(
        @RequestBody
        @Valid topic: TopicRequestDTO,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicResponseDTO> {
        val topicResponse = topicService.add(topic).toResponseDTO()
        val uri = uriComponentsBuilder.path("/topics/${topicResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicResponse)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid topic: TopicRequestUpdateDTO): ResponseEntity<TopicResponseDTO> {
        return ResponseEntity.ok(topicService.update(topic).toResponseDTO())
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = topicService.delete(id)
}