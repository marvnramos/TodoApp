package com.example.tasker.ui.commons.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LabelClickable(
    question: String,
    action: String,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = question,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.width(5.dp))
        ClickableTextComponent(
            text = action,
            onClick = { onClick() }
        )
    }
}