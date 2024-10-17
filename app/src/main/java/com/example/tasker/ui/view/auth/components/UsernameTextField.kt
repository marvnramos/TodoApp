package com.example.tasker.ui.view.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun UsernameTextFieldPreview() {
    var text by remember {
        mutableStateOf("")
    }
    UsernameTextField(
        text = text,
        onValueChange = { text = it }
    )
}

@Composable
fun UsernameTextField(
    text: String,
    onValueChange: (String) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = colorScheme.primary,
        unfocusedIndicatorColor = colorScheme.outline,
        cursorColor = colorScheme.primary,
        focusedContainerColor = colorScheme.surface,
        focusedLabelColor = colorScheme.primary,
        unfocusedLabelColor = colorScheme.onSurface,
        unfocusedPlaceholderColor = colorScheme.onSurface.copy(alpha = 0.6f),
        unfocusedContainerColor = colorScheme.surface
    )
    Column {
        Text(
            text = "Nombre de Usuario",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text = "Escribe tu nombre de usuario",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            colors = colors,

            )
    }
}