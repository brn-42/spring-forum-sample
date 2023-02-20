package com.example.forum.repository

import com.example.forum.model.ForumUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<ForumUser, Long> {
    fun findByEmail(userName: String?): ForumUser?
}