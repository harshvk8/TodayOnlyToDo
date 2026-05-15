package com.harsh.todayonlytodo.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.harsh.todayonlytodo.domain.model.Todo

@Composable
fun TodoItemRow(
    todo: Todo,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = todo.isComplete,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = todo.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = if (todo.isComplete) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                }
            )
        )
    }
}