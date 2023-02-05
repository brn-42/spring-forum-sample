package com.example.forum.utils

import com.example.forum.model.Course

class CourseUtils {
    fun courses(): List<Course> {
        val course1 = Course(
            id = 1,
            name = "Kotlin",
            category = "Spring"
        )

        val course2 = Course(
            id = 2,
            name = "Kotlin",
            category = "Spring"
        )

        return listOf(course1, course2)
    }
}