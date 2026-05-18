package com.harsh.todayonlytodo.data.repository

import com.harsh.todayonlytodo.data.local.TodoEntity
import com.harsh.todayonlytodo.data.local.TodoLocalDataSource
import com.harsh.todayonlytodo.data.mapper.toDomain
import com.harsh.todayonlytodo.domain.model.Todo
import com.harsh.todayonlytodo.domain.repository.TodoRepository
import com.harsh.todayonlytodo.domain.util.DateProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class TodoRepositoryImpl(
    private val localDataSource: TodoLocalDataSource,
    private val dateProvider: DateProvider
) : TodoRepository {

    private val todosFlow = MutableStateFlow<List<TodoEntity>>(emptyList())

    override fun getTodayTodos(): Flow<List<Todo>> {
        return todosFlow.map { todos ->
            todos
                .filter { it.createdDate == dateProvider.today() }
                .sortedWith(
                    compareBy<TodoEntity> { it.isComplete }
                        .thenByDescending { it.id }
                )
                .map { it.toDomain() }
        }
    }

    override suspend fun loadTodos() {
        todosFlow.value = localDataSource.readTodos()
    }

    override suspend fun addTodo(title: String) {
        val cleanTitle = title.trim()

        if (cleanTitle.isBlank()) {
            return
        }

        val currentTodos = todosFlow.value

        val newTodo = TodoEntity(
            id = System.currentTimeMillis(),
            title = cleanTitle,
            isComplete = false,
            createdDate = dateProvider.today()
        )

        val updatedTodos = currentTodos + newTodo
        todosFlow.value = updatedTodos
        localDataSource.saveTodos(updatedTodos)
    }

    override suspend fun setTodoCompleted(
        todoId: Long,
        isComplete: Boolean
    ) {
        val updatedTodos = todosFlow.value.map { todo ->
            if (todo.id == todoId) {
                todo.copy(isComplete = isComplete)
            } else {
                todo
            }
        }

        todosFlow.value = updatedTodos
        localDataSource.saveTodos(updatedTodos)
    }
}