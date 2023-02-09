package com.example.forum.service

import com.example.forum.dto.request.ForumUserRequestDTO
import com.example.forum.mapper.toModel
import com.example.forum.model.ForumUser
import com.example.forum.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    fun list(): List<ForumUser> = userRepository.findAll()

    fun getById(id: Long): Optional<ForumUser> = userRepository.findById(id)

    fun add(forumUser: ForumUserRequestDTO): ForumUser = userRepository.save(forumUser.toModel())
}