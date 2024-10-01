package com.example.tasker.ui.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.example.tasker.ui.view.components.auth.EmailTextField
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@Composable
fun EmailScreen(authVM: AuthViewModel = AuthViewModel()) {
    val email by authVM.email.observeAsState(initial = "")
    val isValidEmail =
        if (email.isNotEmpty()) authVM.isEmailValid(MutableLiveData(email)) else true

    val errorMessage = if (!isValidEmail) "Dirección de correo inválida" else null
    val isError = errorMessage != null
    val isAvailable = email.isNotEmpty() && !isError
    SingUpTemplate(
        onArrowClick = { /*TODO*/ },
        onTextClick = { /*TODO*/ },
        onSubmitClick = { println("código enviado jiji") },
        subWelcomeText = "Ingresa tu correo electrónico",
        textIndicator = null,
        submitText = "Enviar código",
        title = "Recuperar contraseña",
        isAvailable = isAvailable,
        textFieldComponent = {
            EmailTextField(
                text = email,
                isValid = isValidEmail,
                errorMessage = errorMessage,
                onTextChanged = { authVM.onValueChanged(email = it) }
            )
        })
}

@Preview
@Composable
fun EmailScreenPreview() {
    TaskerTheme {
        EmailScreen()
    }
}