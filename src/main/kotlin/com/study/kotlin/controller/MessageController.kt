package com.study.kotlin.controller

import com.study.kotlin.data.Message
import com.study.kotlin.data.MessageTable
import com.study.kotlin.service.MessageService
import org.springframework.web.bind.annotation.*

@RestController
class MessageController(val service: MessageService) {

    @GetMapping
    fun getMessages(): List<Message> {
        return service.findMessages().map {
            it.toMessage()
        }
    }

    @GetMapping(path = ["/{id}"])
    suspend fun getMessageById(@PathVariable id: Int): Message {
        val teste = service.findMessage(id).orElseThrow() //TODO aqui precisa tratar corretamente
        return teste.toMessage()
    }

    @PostMapping
    suspend fun postMessage(@RequestBody message: Message) = service.createMessage(message)
}

fun MessageTable.toMessage(): Message {
    return Message(this.id, this.subject, this.text)
}