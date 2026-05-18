package com.harsh.todayonlytodo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.harsh.todayonlytodo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    init {
        loadAndObserveTodos()
    }

    private fun loadAndObserveTodos() {
        viewModelScope.launch {
            repository.loadTodos()

            repository.getTodayTodos().collect { todos ->
                _uiState.value = _uiState.value.copy(
                    todos = todos,
                    isLoading = false
                )
            }
        }
    }

    fun onInputTextChanged(text: String) {
        _uiState.value = _uiState.value.copy(
            inputText = text
        )
    }

    fun addTodo() {
        val title = _uiState.value.inputText

        viewModelScope.launch {
            repository.addTodo(title)
            _uiState.value = _uiState.value.copy(
                inputText = ""
            )
        }
    }

    fun toggleTodo(
        todoId: Long,
        isComplete: Boolean
    ) {
        viewModelScope.launch {
            repository.setTodoCompleted(
                todoId = todoId,
                isComplete = isComplete
            )
        }
    }
}

class TodoViewModelFactory(
    private val repository: TodoRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}