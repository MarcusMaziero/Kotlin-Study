package com.study.kotlin.service

import com.study.kotlin.configuration.TOPIC_MESSAGE
import com.study.kotlin.data.Message
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaService(private val kafkaTemplate: KafkaTemplate<Int, Any>, private val messageService: MessageService) {

    fun sendMessage(id: Int): Message {
        val message =
            messageService.findMessage(id)?.toMessage() ?: throw Exception("Mensagem não encontrada")
        val messageCurrent = message.copy(sentMessage = true)
        kafkaTemplate.send(ProducerRecord(TOPIC_MESSAGE, id, messageCurrent))
        messageService.createMessage(messageCurrent)
        return message
    }
}