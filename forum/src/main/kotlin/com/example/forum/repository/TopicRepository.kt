package com.example.forum.repository

import com.example.forum.model.Topic
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository: JpaRepository<Topic, Long> {
    @Cacheable("topicsByCourseName")
    fun findByCourseName(courseName: String, pageable: Pageable): Page<Topic>
}