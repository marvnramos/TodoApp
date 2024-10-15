package com.example.tasker.ui.view.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.view.components.TokenTextField
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@Composable
fun SingUpTokenScreen(
    navController: NavHostController,
    authVM: AuthViewModel = AuthViewModel()
) {
    val token by authVM.token.observeAsState(initial = "")

    val isValidToken = authVM.isTokenValid(token)
    val isAvailable = token.isNotEmpty() && isValidToken

    val context = LocalContext.current
    SingUpTemplate(
        onArrowClick = { navController.popBackStack() },
        onTextClick = { navController.navigate("Login") },
        onSubmitClick = { pushToSingUPScreen(navController) },
        subWelcomeText = "Verificación de correo",
        textIndicator = "Ingresa el código que recibiste \nen tu correo electrónico",
        submitText = "Verificar",
        isAvailable = isAvailable,
        textFieldComponent = {
            TokenTextField(
                text = token,
                isValid = if (token.isNotEmpty()) isValidToken else true,
                isError = token.isNotEmpty() && !isValidToken
            ) {
                authVM.onValueChanged(token = it)
            }
        }
    )
}

private fun pushToSingUPScreen(navController: NavHostController) {
    navController.navigate("SingUp")
}

@Preview
@Composable
fun SingUpTokenScreenPreview() {
    TaskerTheme {
        val navController = rememberNavController()
        SingUpTokenScreen(navController)
    }
}