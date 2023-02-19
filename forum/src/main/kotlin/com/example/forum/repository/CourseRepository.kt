package com.example.forum.repository

import com.example.forum.model.Course
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository: JpaRepository<Course, Long> {

    @Cacheable("findAllCourses")
    override fun findAll(): List<Course>

    @Cacheable("courseById")
    override fun getById(id: Long): Course
}