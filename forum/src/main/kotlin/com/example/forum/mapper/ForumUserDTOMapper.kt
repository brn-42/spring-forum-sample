package com.example.forum.mapper

import com.example.forum.dto.request.ForumUserRequestDTO
import com.example.forum.dto.response.ForumUserResponseDTO
import com.example.forum.model.ForumUser
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun ForumUser.toResponseDTO() =
    ForumUserResponseDTO(
        id = id,
        name = name,
        email = email
    )

fun ForumUserRequestDTO.toModel() =
    ForumUser(
        name = name,
        email = email,
        password = BCryptPasswordEncoder().encode(password)
    )