package com.example.forum.service

import com.example.forum.dto.request.ForumUserRequestDTO
import com.example.forum.mapper.toModel
import com.example.forum.model.ForumUser
import com.example.forum.repository.UserRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {

    @Cacheable("findAllForumUsers")
    fun list(): List<ForumUser> = userRepository.findAll()

    @Cacheable("forumUserById")
    fun getById(id: Long): Optional<ForumUser> = userRepository.findById(id)

    @CacheEvict(
        value = ["findAllForumUsers", "forumUserById"],
        allEntries = true
    )
    fun add(forumUser: ForumUserRequestDTO): ForumUser = userRepository.save(forumUser.toModel())

    @CacheEvict(
        value = ["findAllForumUsers", "forumUserById"],
        allEntries = true
    )
    fun delete(id: Long) = userRepository.deleteById(id)
}