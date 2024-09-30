package com.example.tasker.ui.view.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import com.example.tasker.ui.view.components.auth.PasswordTextField
import com.example.tasker.ui.view.components.auth.UsernameTextField
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@Composable
fun SingUpScreen(viewModel: AuthViewModel = AuthViewModel()) {
    val context = LocalContext.current

    val password by viewModel.password.observeAsState(initial = "")
    var comparePasswordErrorMessages by remember { mutableStateOf<List<String>>(emptyList()) }
    val username by viewModel.username.observeAsState(initial = "")
    var errorMessages by remember { mutableStateOf<List<String>>(emptyList()) }
    val confirmedPassword by viewModel.confirmedPassword.observeAsState(initial = "")

    val isValid = if (password.isNotEmpty()) {
        errorMessages = viewModel.isPasswordValid(MutableLiveData(password))
        errorMessages.isEmpty()
    } else {
        errorMessages = emptyList()
        true
    }

    val samePassword = if (confirmedPassword.isNotEmpty()) {
        if (!viewModel.comparePasswords(
                MutableLiveData(password),
                MutableLiveData(confirmedPassword)
            )
        ) {
            comparePasswordErrorMessages = listOf("Las contraseñas no coinciden.")
            false
        } else {
            comparePasswordErrorMessages = emptyList()
            true
        }
    } else {
        comparePasswordErrorMessages = emptyList()
        true
    }

    val isAvailable = username.isNotEmpty() && password.isNotEmpty() && confirmedPassword.isNotEmpty() && isValid && samePassword

    SingUpTemplate(
        onArrowClick = { Toast.makeText(context, "Arrow Clicked", Toast.LENGTH_SHORT).show() },
        onTextClick = { Toast.makeText(context, "Text Clicked", Toast.LENGTH_SHORT).show() },
        onSubmitClick = { Toast.makeText(context, "Submit Clicked", Toast.LENGTH_SHORT).show() },
        subWelcomeText = "Ingresa tus datos",
        textIndicator = null,
        submitText = "Registrarse",
        isAvailable = isAvailable
    ) {
        UsernameTextField(
            text = username,
            onValueChange = { viewModel.onValueChanged(username = it) }
        )
        PasswordTextField(
            text = password,
            availableValidation = true,
            isValid = isValid,
            errorMessages = errorMessages,
            onTextChanged = { viewModel.onValueChanged(password = it) }
        )

        PasswordTextField(
            label = "Confirmar contraseña",
            text = confirmedPassword,
            availableValidation = true,
            isValid = samePassword,
            errorMessages = comparePasswordErrorMessages,
            onTextChanged = { viewModel.onValueChanged(confirmedPassword = it) }
        )
    }
}

@Preview
@Composable
fun SingUpScreenPreview() {
    TaskerTheme {
        SingUpScreen()
    }
}