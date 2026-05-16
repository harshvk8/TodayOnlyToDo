package com.harsh.todayonlytodo.data.local

data class TodoEntity(
    val id: Long = 0,
    val title: String,
    val isComplete: Boolean,
    val createdDate: String
)