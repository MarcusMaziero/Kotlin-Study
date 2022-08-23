package com.study.kotlin.service

import com.study.kotlin.configuration.TOPIC_MESSAGE
import com.study.kotlin.data.Message
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaService(private val kafkaTemplate: KafkaTemplate<Int, Any>, private val messageService: MessageService) {

    fun sendMessage(id: Int): Message {
        val message = messageService.findMessage(id).let { it?.toMessage() } ?: throw Exception("Mensagem não encontrada")
        kafkaTemplate.send(ProducerRecord(TOPIC_MESSAGE, id, message))
        message.sendMessage = true
        //duvida é correto fazer isso?
        //ou deve criar um objeto novo de cópia e ai sim setar o true?
        messageService.createMessage(message)
        return message
    }
}