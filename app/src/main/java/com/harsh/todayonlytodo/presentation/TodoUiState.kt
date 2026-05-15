package com.harsh.todayonlytodo.presentation

import com.harsh.todayonlytodo.domain.model.Todo

data class TodoUiState(
    val todos: List<Todo> = emptyList(),
    val inputText: String = "",
    val isLoading: Boolean = true
)