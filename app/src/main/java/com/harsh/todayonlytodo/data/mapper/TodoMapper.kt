package com.harsh.todayonlytodo.data.mapper

import com.harsh.todayonlytodo.data.local.TodoEntity
import com.harsh.todayonlytodo.domain.model.Todo

fun TodoEntity.toDomain(): Todo {
    return Todo(
        id = id,
        title = title,
        isComplete = isComplete,
        createdDate = createdDate
    )
}