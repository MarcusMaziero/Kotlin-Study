package com.study.kotlin.service

import com.study.kotlin.data.Message
import com.study.kotlin.data.MessageTable
import com.study.kotlin.data.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService(val repository: MessageRepository) {

    fun findMessages(): List<MessageTable> = repository.findMessages()

    fun createMessage(message: Message) {
        val messageTable = MessageTable(message.id, message.subject, message.text)
        repository.save(messageTable)
    }
}