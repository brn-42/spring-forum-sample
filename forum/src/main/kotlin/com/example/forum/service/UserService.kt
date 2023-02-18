package com.example.forum.service

import com.example.forum.dto.request.ForumUserRequestDTO
import com.example.forum.mapper.toModel
import com.example.forum.model.ForumUser
import com.example.forum.repository.ForumUserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val forumUserRepository: ForumUserRepository) {

    fun list(): List<ForumUser> = forumUserRepository.findAll()

    fun getById(id: Long): Optional<ForumUser> = forumUserRepository.findById(id)

    fun add(forumUser: ForumUserRequestDTO): ForumUser = forumUserRepository.save(forumUser.toModel())
}