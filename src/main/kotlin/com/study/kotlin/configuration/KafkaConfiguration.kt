package com.study.kotlin.configuration

import org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaConfiguration(@Value("\${kafka.bootstrapAddress}") private val servers: String) {

    @Bean
    fun kafkaAdmin(): KafkaAdmin = KafkaAdmin(mutableMapOf<String, Any?>(BOOTSTRAP_SERVERS_CONFIG to this.servers))
}