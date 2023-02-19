package com.example.forum.model

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String,
    var message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val course: Course,

    @ManyToOne
    val author: ForumUser,

    @Enumerated(value = EnumType.STRING)
    val status: Status = Status.UNANSWERED,

    @OneToMany(mappedBy = "topic")
    val responses: List<Response> = ArrayList()
): Serializable

