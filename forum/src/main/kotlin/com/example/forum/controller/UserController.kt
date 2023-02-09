package com.example.forum.controller

import com.example.forum.dto.request.ForumUserRequestDTO
import com.example.forum.dto.response.ForumUserResponseDTO
import com.example.forum.mapper.toResponseDTO
import com.example.forum.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun list(): List<ForumUserResponseDTO> = userService.list().map { it.toResponseDTO() }

    @PostMapping
    @Transactional
    fun add(
        @RequestBody
        @Valid
        user: ForumUserRequestDTO,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<ForumUserResponseDTO> {
        val userResponse = userService.add(user).toResponseDTO()
        val uri = uriComponentsBuilder.path("/topics/${userResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(userResponse)
    }
}