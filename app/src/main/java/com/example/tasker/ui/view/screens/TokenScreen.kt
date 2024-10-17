package com.example.tasker.ui.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.view.Routes
import com.example.tasker.ui.view.components.TokenTextField
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@Preview
@Composable
fun TokenScreenPreview() {
    TaskerTheme {
        TokenScreen(navController = rememberNavController())
    }
}

@Composable
fun TokenScreen(
    navController: NavHostController,
    authVM: AuthViewModel = AuthViewModel()
) {
    val token by authVM.token.observeAsState(initial = "")

    val isValidToken = authVM.isTokenValid(token)
    val isAvailable = token.isNotEmpty() && isValidToken

    Template(
        onArrowClick = { navController.popBackStack() },
        onTextClick = { navController.popBackStack() },
        onSubmitClick = { pushToForgotPasswordScreen(navController) },
        subWelcomeText = "",
        textIndicator = "Ingresa el código que recibiste \nen tu correo electrónico",
        submitText = "Verificar",
        title = "Recuperar contraseña",
        isAvailable = isAvailable,
        textFieldComponent = {
            TokenTextField(
                text = token,
                isValid = if (token.isNotEmpty()) isValidToken else true,
                isError = token.isNotEmpty() && !isValidToken
            ) {
                authVM.onValueChanged(token = it)
            }
        },
        loginAvailable = false
    )
}

private fun pushToForgotPasswordScreen(navController: NavHostController) {
    navController.navigate(Routes.FORGOT_PASSWORD)
}