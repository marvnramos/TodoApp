package com.example.tasker.ui.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.view.Routes
import com.example.tasker.ui.view.components.auth.EmailTextField
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@Composable
fun EmailScreen(
    navController: NavHostController,
    authVM: AuthViewModel = AuthViewModel()
) {
    val email by authVM.email.observeAsState(initial = "")
    val isValidEmail =
        if (email.isNotEmpty()) authVM.isEmailValid(MutableLiveData(email)) else true

    val errorMessage = if (!isValidEmail) "Dirección de correo inválida" else null
    val isError = errorMessage != null
    val isAvailable = email.isNotEmpty() && !isError
    Template(
        onArrowClick = { navController.popBackStack() },
        onTextClick = { navController.popBackStack() },
        onSubmitClick = { pushToTokenScreen(navController) },
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
        },
        loginAvailable = false
    )
}

@Preview
@Composable
fun EmailScreenPreview() {
    TaskerTheme {
        EmailScreen(navController = rememberNavController())
    }
}

private fun pushToTokenScreen(navController: NavHostController) {
    navController.navigate(Routes.TOKEN_FORGOT_PASSWORD_FLOW)
}