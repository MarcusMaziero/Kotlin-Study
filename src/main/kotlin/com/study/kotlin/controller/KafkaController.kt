package com.study.kotlin.controller

import com.study.kotlin.data.Message
import com.study.kotlin.service.KafkaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message/kafka")
class KafkaController(private val kafkaService: KafkaService) {

    @GetMapping("/{id}")
    fun sendMessage(@PathVariable id: Int): Message = kafkaService.sendMessage(id)
}