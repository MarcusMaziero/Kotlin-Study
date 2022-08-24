package com.study.kotlin.data.repository

import com.study.kotlin.data.MessageTable
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<MessageTable, Int> {

    @Query("select * from message")
    fun findMessages(): List<MessageTable>
}