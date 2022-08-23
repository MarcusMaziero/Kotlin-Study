package com.study.kotlin.service

import com.study.kotlin.data.Message
import com.study.kotlin.data.MessageTable
import com.study.kotlin.data.repository.MessageRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageService(private val repository: MessageRepository) {

    fun findMessages(): List<MessageTable> = repository.findMessages()

    fun findMessage(id: Int): Optional<MessageTable> = repository.findById(id)

    fun createMessage(message: Message) {
        val messageTable = MessageTable(message.id, message.subject, message.text)
        repository.save(messageTable)
    }

    fun deleteMessage(id: Int) = repository.deleteById(id)
}

fun MessageTable.toMessage(): Message = Message(this.id, this.subject, this.text)