/*package com.harsh.todayonlytodo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query(
        """
        SELECT * FROM todos 
        WHERE createdDate = :date 
        ORDER BY isComplete ASC, createdAtMillis DESC
        """
    )
    fun getTodosForDate(date: String): Flow<List<TodoEntity>>

    @Insert
    suspend fun insertTodo(todo: TodoEntity)

    @Query("UPDATE todos SET isComplete = :isComplete WHERE id = :id")
    suspend fun updateCompletion(
        id: Long,
        isComplete: Boolean
    )
}