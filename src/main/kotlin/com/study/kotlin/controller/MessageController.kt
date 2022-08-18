package com.study.kotlin.controller

import com.study.kotlin.data.Message
import com.study.kotlin.service.MessageService
import org.springframework.web.bind.annotation.*

@RestController
class MessageController(val service: MessageService) {

    //:TODO precisa criar um literal function para essas conversões - acho que vai ficar melhor
    @GetMapping
    fun getMessages(): List<Message> {
        return service.findMessages().map {
            Message(it.id, it.subject, it.text)
        }
    }

    @GetMapping(path = ["/{id}"])
    fun getMessageById(@PathVariable id: Int): Message {
        val teste = service.findMessage(id).orElseThrow() //TODO aqui precisa tratar corretamente
        return Message(teste.id, teste.subject, teste.text)
    }

    @PostMapping
    fun posMessage(@RequestBody message: Message) {
        service.createMessage(message)
    }
}