package com.harsh.todayonlytodo.data.local

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class TodoLocalDataSource(
    context: Context
) {
    private val file = context.applicationContext.filesDir.resolve("todos.json")

    suspend fun readTodos(): List<TodoEntity> = withContext(Dispatchers.IO) {
        if (!file.exists()) {
            return@withContext emptyList()
        }

        val jsonText = file.readText()
        if (jsonText.isBlank()) {
            return@withContext emptyList()
        }

        val jsonArray = JSONArray(jsonText)
        buildList {
            for (index in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(index)
                add(
                    TodoEntity(
                        id = item.getLong("id"),
                        title = item.getString("title"),
                        isComplete = item.getBoolean("isComplete"),
                        createdDate = item.getString("createdDate")
                    )
                )
            }
        }
    }

    suspend fun saveTodos(todos: List<TodoEntity>) = withContext(Dispatchers.IO) {
        val jsonArray = JSONArray()

        todos.forEach { todo ->
            val item = JSONObject()
                .put("id", todo.id)
                .put("title", todo.title)
                .put("isComplete", todo.isComplete)
                .put("createdDate", todo.createdDate)

            jsonArray.put(item)
        }

        file.writeText(jsonArray.toString())
    }
}