package com.example.forum.repository

import com.example.forum.model.ForumUser
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<ForumUser, Long> {

    @Cacheable("findAllForumUsers")
    override fun findAll(): List<ForumUser>

    @Cacheable("forumUserById")
    override fun getById(id: Long): ForumUser
}