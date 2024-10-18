package com.example.tasker.ui.home.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.core.content.ContextCompat
import com.example.tasker.R


@Composable
fun AddButtonComponent(onClick: () -> Unit) {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = { onClick() },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,

    ) {
        Icon(Icons.Filled.Add, contentDescription = "Floating action button.")
    }
}