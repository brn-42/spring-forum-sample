package com.example.forum.controller

import com.example.forum.service.AppService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/clean-cache")
class AppController(private val appService: AppService) {

    @GetMapping
    fun cleanCache() = appService.cleanCache()
}