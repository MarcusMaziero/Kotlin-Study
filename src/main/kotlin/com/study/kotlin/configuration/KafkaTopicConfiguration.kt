package com.study.kotlin.configuration

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

const val TOPIC_MESSAGE = "message_kotlin_topic"

@Configuration
class KafkaTopicConfiguration {

    @Bean
    fun messageTopic(): NewTopic = NewTopic(TOPIC_MESSAGE, 1, 1)
}