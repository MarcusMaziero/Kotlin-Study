package com.study.kotlin.controller

import com.study.kotlin.data.Message
import com.study.kotlin.data.MessageTable
import com.study.kotlin.service.MessageService
import com.study.kotlin.service.toMessage
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/message")
class MessageController(private val service: MessageService) {

    @GetMapping
    fun getMessages(): List<Message> = service.findMessages().map(MessageTable::toMessage)

    @GetMapping("/{id}")
    fun getMessageById(@PathVariable id: Int): Message? =
        service.findMessage(id).let { it?.toMessage() } ?: throw Exception("Mensagem não encontrada")

    @PostMapping
    fun postMessage(@RequestBody message: Message) = service.createMessage(message)

    @DeleteMapping("/{id}")
    fun deleteMessage(@PathVariable id: Int) = service.deleteMessage(id)
}