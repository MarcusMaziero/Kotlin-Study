package com.study.kotlin.configuration

import com.study.kotlin.configuration.convert.KeySerializable
import com.study.kotlin.configuration.convert.ValueSerializable
import org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG
import org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG
import org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProduceConfiguration(@Value("\${kafka.bootstrapAddress}") private val servers: String) {

    @Bean
    fun serializable(): ProducerFactory<Int, Any> {
        val configsSerializable: MutableMap<String, Any?> = mutableMapOf(
            BOOTSTRAP_SERVERS_CONFIG to this.servers,
            KEY_SERIALIZER_CLASS_CONFIG to KeySerializable::class.java,
            VALUE_SERIALIZER_CLASS_CONFIG to ValueSerializable::class.java
        )
        return DefaultKafkaProducerFactory(configsSerializable)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<Int, Any> {
        return KafkaTemplate(this.serializable())
    }
}