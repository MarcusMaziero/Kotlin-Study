package com.study.kotlin.configuration

import com.study.kotlin.configuration.convert.GenericDeserializable
import org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties

@Configuration
class KafkaConsumerConfiguration(@Value("\${kafka.bootstrapAddress}") private val servers: String) {

    @Bean
    fun deserializable(): ConsumerFactory<Any?, Any?> {
        return DefaultKafkaConsumerFactory(
            mutableMapOf<String, Any>(
                BOOTSTRAP_SERVERS_CONFIG to this.servers,
                GROUP_ID_CONFIG to "kotlin",
                KEY_DESERIALIZER_CLASS_CONFIG to GenericDeserializable::class.java,
                VALUE_DESERIALIZER_CLASS_CONFIG to GenericDeserializable::class.java,
                AUTO_OFFSET_RESET_CONFIG to "earliest"
            )
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<Any, Any>? {
        return ConcurrentKafkaListenerContainerFactory<Any, Any>()
            .apply {
                consumerFactory = deserializable()
                containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
                containerProperties.isSyncCommits = true
            }
    }
}