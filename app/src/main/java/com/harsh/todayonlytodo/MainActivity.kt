package com.harsh.todayonlytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import com.harsh.todayonlytodo.di.AppModule
import com.harsh.todayonlytodo.presentation.TodoViewModel
import com.harsh.todayonlytodo.presentation.TodoViewModelFactory
import com.harsh.todayonlytodo.presentation.screen.TodayTodoScreen
import com.harsh.todayonlytodo.ui.theme.TodayOnlyTodoTheme

class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(
            repository = AppModule.provideTodoRepository(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("todo_prefs", MODE_PRIVATE)

        setContent {
            val systemDark = isSystemInDarkTheme()
            var isDarkMode by rememberSaveable {
                mutableStateOf(prefs.getBoolean("dark_mode", systemDark))
            }

            TodayOnlyTodoTheme(darkTheme = isDarkMode) {
                TodayTodoScreen(
                    viewModel = todoViewModel,
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = {
                        isDarkMode = !isDarkMode
                        prefs.edit { putBoolean("dark_mode", isDarkMode) }
                    }
                )
            }
        }
    }
}