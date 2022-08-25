package com.study.kotlin.configuration.convert

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.Deserializer

class GenericDeserializable() : Deserializer<Any> {
    private val objectMapper = ObjectMapper()
    override fun deserialize(topic: String?, data: ByteArray?): Any =
        data?.let { objectMapper.readValue(it, Any::class.java) }
            ?: throw Exception("Erro ao deserializar message kafka!")
}