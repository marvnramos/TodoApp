package com.example.tasker.ui.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.R
import com.example.tasker.ui.view.components.auth.EmailTextField
import com.example.tasker.ui.view.theme.TaskerTheme

@Composable
fun AuthScreen() {
    Column(modifier = Modifier.padding(45.dp)) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "¡Bienvenid@!",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Inicia sesión en tu cuenta",
            style = MaterialTheme.typography.titleLarge
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "",
                modifier = Modifier
                    .height(175.dp)
                    .width(175.dp)
            )
        }

        EmailTextField(text = "", isValid = true, errorMessage = "", onTextChanged = {})
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    TaskerTheme {
        AuthScreen()
    }
}
