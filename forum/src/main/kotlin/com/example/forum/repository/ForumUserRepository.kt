package com.example.forum.repository

import com.example.forum.model.ForumUser
import org.springframework.data.jpa.repository.JpaRepository

interface ForumUserRepository: JpaRepository<ForumUser, Long>