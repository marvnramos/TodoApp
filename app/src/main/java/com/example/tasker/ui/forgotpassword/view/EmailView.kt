package com.example.tasker.ui.forgotpassword.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.view.Routes
import com.example.tasker.ui.commons.components.EmailTextField
import com.example.tasker.ui.theme.TaskerTheme
import com.example.tasker.ui.commons.TemplateView
import com.example.tasker.ui.forgotpassword.viewmodel.ForgotPasswordViewModel

@Composable
fun EmailView(
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel = ForgotPasswordViewModel()
) {
    val email by viewModel.email.observeAsState(initial = "")
    val isValidEmail =
        if (email.isNotEmpty()) viewModel.isEmailValid(MutableLiveData(email)) else true

    val errorMessage = if (!isValidEmail) "Dirección de correo inválida" else null
    val isError = errorMessage != null
    val isAvailable = email.isNotEmpty() && !isError
    TemplateView(
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
                onTextChanged = { viewModel.onValueChanged(email = it) }
            )
        },
        loginAvailable = false
    )
}

@Preview
@Composable
fun EmailScreenPreview() {
    TaskerTheme {
        EmailView(navController = rememberNavController())
    }
}

private fun pushToTokenScreen(navController: NavHostController) {
    navController.navigate(Routes.TOKEN_FORGOT_PASSWORD_FLOW)
}