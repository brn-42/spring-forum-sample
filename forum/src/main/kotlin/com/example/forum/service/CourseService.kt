package com.example.forum.service

import com.example.forum.model.Course
import com.example.forum.utils.CourseUtils
import org.springframework.stereotype.Service

@Service
class CourseService {

    private var courses = CourseUtils().courses()

    fun list(): List<Course> {
        return courses
    }

    fun getById(id: Long): Course {
        return courses.first { it.id == id }
    }

    fun add(course: Course) {
        courses.plus(course)
    }
}