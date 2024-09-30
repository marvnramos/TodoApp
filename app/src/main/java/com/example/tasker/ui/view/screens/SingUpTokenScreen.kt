package com.example.tasker.ui.view.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasker.ui.view.components.TokenTextField
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@Composable
fun SingUpTokenScreen(authVM: AuthViewModel = AuthViewModel()) {
    val token by authVM.token.observeAsState(initial = "")
    val isValidToken = authVM.isTokenValid(token)

    val context = LocalContext.current
    SingUpTemplate(
        onArrowClick = { Toast.makeText(context, "Arrow Clicked", Toast.LENGTH_SHORT).show() },
        onTextClick = { Toast.makeText(context, "Text Clicked", Toast.LENGTH_SHORT).show() },
        onSubmitClick = { Toast.makeText(context, "Submit Clicked", Toast.LENGTH_SHORT).show() },
        subWelcomeText = "Verificación de correo",
        textIndicator = "Ingresa el código que recibiste \nen tu correo electrónico",
        submitText = "Verificar",
        isAvailable = isValidToken,
    ) {
        TokenTextField(
            text = token,
            isValid = isValidToken,
            isError = token.isNotEmpty() && !isValidToken
        ) {
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