package com.study.kotlin.controller

import com.study.kotlin.data.Message
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController {
    @GetMapping
    fun messages(): List<Message> = listOf(
        Message(1, "Teste", "eita pega"),
        Message(subject = "teste 2", id = 2, text = "eita nós")
    )
}