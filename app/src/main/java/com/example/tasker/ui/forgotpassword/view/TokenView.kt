package com.example.tasker.ui.forgotpassword.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.view.Routes
import com.example.tasker.ui.commons.components.TokenTextField
import com.example.tasker.ui.theme.TaskerTheme
import com.example.tasker.ui.auth.viewmodel.AuthViewModel
import com.example.tasker.ui.commons.TemplateView

@Preview
@Composable
fun TokenScreenPreview() {
    TaskerTheme {
        TokenView(navController = rememberNavController())
    }
}

@Composable
fun TokenView(
    navController: NavHostController,
    authVM: AuthViewModel = AuthViewModel()
) {
    val token by authVM.token.observeAsState(initial = "")

    val isValidToken = authVM.isTokenValid(token)
    val isAvailable = token.isNotEmpty() && isValidToken

    TemplateView(
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