package com.harsh.todayonlytodo.domain.repository

import com.harsh.todayonlytodo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getTodayTodos(): Flow<List<Todo>>

    suspend fun loadTodos()

    suspend fun addTodo(title: String)

    suspend fun setTodoCompleted(
        todoId: Long,
        isComplete: Boolean
    )
}