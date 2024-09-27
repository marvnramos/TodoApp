package com.example.tasker.ui.view.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasker.ui.view.components.TokenTextField
import com.example.tasker.ui.view.components.auth.EmailTextField
import com.example.tasker.ui.view.components.auth.SingUpTemplate
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@Composable
fun SingUpTokenScreen(authVM: AuthViewModel = AuthViewModel()) {
    val token by authVM.token.observeAsState(initial = "")
    val isValidToken =
        if (token.isNotEmpty()) authVM.isTokenValid(token) else true

    val isAvailable = token.isNotEmpty() && isValidToken

    val context = LocalContext.current
    SingUpTemplate(
        onArrowClick = { Toast.makeText(context, "Arrow Clicked", Toast.LENGTH_SHORT).show() },
        onTextClick = { Toast.makeText(context, "Text Clicked", Toast.LENGTH_SHORT).show() },
        onSubmitClick = { Toast.makeText(context, "Submit Clicked", Toast.LENGTH_SHORT).show() },
        subWelcomeText = "Verificación de correo",
        textIndicator = "Ingresa el código que recibiste \nen tu correo electrónico",
        submitText = "Verificar",
        isAvailable = isAvailable,
    ) {
        TokenTextField(text = token) {
            authVM.onValueChanged(token = it)
        }
    }
}

@Preview
@Composable
fun SingUpTokenScreenPreview() {
    TaskerTheme {
        SingUpTokenScreen()
    }
}