package com.study.kotlin.service.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.kotlin.configuration.TOPIC_MESSAGE
import com.study.kotlin.data.Message
import com.study.kotlin.service.MessageService
import com.study.kotlin.service.toMessage
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class MessageConsumer(private val objectMapper: ObjectMapper, private val messageService: MessageService) {

    @KafkaListener(topics = [TOPIC_MESSAGE], groupId = "kotlin")
    fun listenerMessage(consumerRecord: ConsumerRecord<Any, Any>, ack: Acknowledgment) {
        val message = consumerRecord.payload<Message>()
        val messageSaved =
            messageService.findMessage(message.id)?.toMessage() ?: throw Exception("Erro ao buscar Messagem")
        messageService.createMessage(messageSaved.copy(messageViewed = true))
        ack.acknowledge()
    }

    private inline fun <reified T> ConsumerRecord<Any, Any>.payload(): T {
        return objectMapper.convertValue(this.value(), T::class.java)
            ?: throw Exception("Erro ao mapear payload de Mesagem")
    }
}