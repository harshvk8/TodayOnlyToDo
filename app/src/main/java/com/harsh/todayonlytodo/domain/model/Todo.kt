package com.harsh.todayonlytodo.domain.model


data class Todo(
    val id: Long,
    val title: String,
    val isComplete: Boolean,
    val createdDate: String
)