package com.harsh.todayonlytodo.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harsh.todayonlytodo.presentation.TodoViewModel
import com.harsh.todayonlytodo.presentation.components.AddTodoBar
import com.harsh.todayonlytodo.presentation.components.EmptyState
import com.harsh.todayonlytodo.presentation.components.TodoItemRow

@Composable
fun TodayTodoScreen(
    viewModel: TodoViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            Text(
                text = "Today Only Todo",
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Only today’s tasks are shown. A new day starts fresh.",
                modifier = Modifier.padding(top = 6.dp),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            AddTodoBar(
                inputText = uiState.inputText,
                onInputTextChanged = viewModel::onInputTextChanged,
                onAddClick = viewModel::addTodo
            )

            Spacer(modifier = Modifier.height(20.dp))

            when {
                uiState.isLoading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.todos.isEmpty() -> {
                    EmptyState()
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        items(
                            items = uiState.todos,
                            key = { todo -> todo.id }
                        ) { todo ->
                            TodoItemRow(
                                todo = todo,
                                onCheckedChange = { checked ->
                                    viewModel.toggleTodo(
                                        todoId = todo.id,
                                        isComplete = checked
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}