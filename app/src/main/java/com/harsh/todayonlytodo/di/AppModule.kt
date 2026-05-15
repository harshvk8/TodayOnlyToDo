package com.harsh.todayonlytodo.di

import android.content.Context
import com.harsh.todayonlytodo.data.local.TodoLocalDataSource
import com.harsh.todayonlytodo.data.repository.TodoRepositoryImpl
import com.harsh.todayonlytodo.domain.util.SystemDateProvider

object AppModule {

    @Volatile
    private var repository: TodoRepository? = null

    fun provideTodoRepository(context: Context): TodoRepository {
        return repository ?: synchronized(this) {
            repository ?: TodoRepositoryImpl(
                localDataSource = TodoLocalDataSource(context),
                dateProvider = SystemDateProvider()
            ).also {
                repository = it
            }
        }
    }
}