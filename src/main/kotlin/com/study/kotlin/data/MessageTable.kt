package com.study.kotlin.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("message")
data class MessageTable(
    @Id @Column("id") val id: Int,
    @Column("subject") val subject: String?,
    @Column("text") val text: String,
    @Column("send_message") val sendMessage: Boolean = false
)