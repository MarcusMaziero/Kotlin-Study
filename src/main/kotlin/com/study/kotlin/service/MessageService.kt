package com.study.kotlin.service

import com.study.kotlin.data.Message
import com.study.kotlin.data.MessageTable
import com.study.kotlin.data.repository.MessageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageService(private val repository: MessageRepository) {

    fun findMessages(): List<MessageTable> = repository.findMessages()

    fun findMessage(id: Int): MessageTable? = repository.findByIdOrNull(id)

    fun createMessage(message: Message) {
        val messageTable = MessageTable(message.id, message.subject, message.text, message.sendMessage)
        repository.save(messageTable)
    }

    fun deleteMessage(id: Int) = repository.deleteById(id)
}

fun MessageTable.toMessage(): Message = Message(this.id, this.subject, this.text, this.sendMessage)