package com.example.forum.service

import com.example.forum.model.Course
import com.example.forum.repository.CourseRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CourseService(private val courseRepository: CourseRepository) {

    fun list(): List<Course> = courseRepository.findAll()

    fun getById(id: Long): Optional<Course> = courseRepository.findById(id)

    fun add(course: Course): Course = courseRepository.save(course)
}