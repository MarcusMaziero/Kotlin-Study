package com.study.kotlin.controller

import com.study.kotlin.data.Message
import com.study.kotlin.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(val service: MessageService) {

    @GetMapping
    fun getMessages(): List<Message> {
        return service.findMessages().map {
            Message(it.id, it.subject, it.text)
        }
    }

    @PostMapping
    fun posMessage(@RequestBody message: Message) {
        service.createMessage(message)
    }
}