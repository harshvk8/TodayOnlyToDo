package com.harsh.todayonlytodo.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harsh.todayonlytodo.presentation.TodoViewModel
import com.harsh.todayonlytodo.presentation.components.AddTodoBar
import com.harsh.todayonlytodo.presentation.components.EmptyState
import com.harsh.todayonlytodo.presentation.components.TodoItemRow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayTodoScreen(
    viewModel: TodoViewModel,
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val today = remember {
        LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMMM d"))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("Today", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                        Text(
                            today,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onToggleDarkMode) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Filled.LightMode
                            else Icons.Filled.DarkMode,
                            contentDescription = if (isDarkMode) "Switch to light mode"
                            else "Switch to dark mode"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            AddTodoBar(
                text = uiState.inputText,
                onTextChange = viewModel::onInputTextChanged,
                onAdd = viewModel::addTodo
            )
            Spacer(Modifier.height(16.dp))

            if (uiState.todos.isEmpty()) {
                EmptyState()
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(uiState.todos, key = { it.id }) { todo ->
                        TodoItemRow(
                            todo = todo,
                            onToggle = { isComplete ->
                                viewModel.toggleTodo(todo.id, isComplete)
                            }
                        )
                    }
                }
            }
        }
    }
}