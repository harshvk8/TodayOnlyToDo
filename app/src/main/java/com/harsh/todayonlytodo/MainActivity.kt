package com.harsh.todayonlytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.harsh.todayonlytodo.di.AppModule
import com.harsh.todayonlytodo.presentation.TodoViewModel
import com.harsh.todayonlytodo.presentation.TodoViewModelFactory
import com.harsh.todayonlytodo.ui.theme.TodayOnlyTodoTheme

class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(
            repository = AppModule.provideTodoRepository(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodayOnlyTodoTheme {
                TodayTodoApp(
                    viewModel = todoViewModel
                )
            }
        }
    }
}