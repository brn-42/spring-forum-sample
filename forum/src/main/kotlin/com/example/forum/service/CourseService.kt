package com.example.forum.service

import com.example.forum.model.Course
import com.example.forum.repository.CourseRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CourseService(private val courseRepository: CourseRepository) {

    @Cacheable("findAllCourses")
    fun list(): List<Course> = courseRepository.findAll()

    @Cacheable("courseById")
    fun getById(id: Long): Optional<Course> = courseRepository.findById(id)

    @CacheEvict(
        value = ["findAllCourses", "courseById"],
        allEntries = true
    )
    fun add(course: Course): Course = courseRepository.save(course)
}