package com.example.forum.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service

@Service
class AppService {

    @CacheEvict(
        value = [
            "findAllCourses",
            "courseById",
            "topicsByCourseName",
            "findAllTopics",
            "getById",
            "findAllForumUsers",
            "forumUserById"
                ],
        allEntries = true
    )
    fun cleanCache() = "OK"
}