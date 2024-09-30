package com.example.tasker.ui.view.components.auth

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun VisibilityButton(
    btnIcon: ImageVector,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(5.dp),
    ) {
        Icon(
            imageVector = btnIcon,
            contentDescription = "Password visibility off button",
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}

