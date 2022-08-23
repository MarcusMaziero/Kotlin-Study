package com.study.kotlin.configuration.convert

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Serializer

class ValueSerializable() : Serializer<Any> {

    private val objectMapper: ObjectMapper = ObjectMapper()

    override fun serialize(topic: String?, data: Any?): ByteArray? =
        data?.let { objectMapper.writeValueAsBytes(it) } ?: throw Exception("Erro ao serializar value kafka!")
}