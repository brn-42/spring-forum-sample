package com.example.forum.service

import com.example.forum.model.User
import com.example.forum.utils.UserUtil
import org.springframework.stereotype.Service

@Service
class UserService {
    private var users = UserUtil().users()

    fun list(): List<User> {
        return users
    }

    fun getById(id: Long): User {
        return users.first { it.id == id }
    }

    fun add(course: User) {
        users.plus(course)
    }
}