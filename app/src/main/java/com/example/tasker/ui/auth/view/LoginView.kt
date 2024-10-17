package com.example.tasker.ui.auth.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.tasker.ui.commons.components.LabelClickable
import com.example.tasker.ui.commons.components.PasswordTextField
import com.example.tasker.ui.commons.components.SubmitButton
import com.example.tasker.ui.auth.components.UsernameTextField
import com.example.tasker.ui.commons.components.WelcomeText
import com.example.tasker.ui.auth.viewmodel.AuthViewModel

@Composable
fun LoginView(
    navController: NavHostController,
    authVM: AuthViewModel = AuthViewModel()
) {
    val username by authVM.username.observeAsState(initial = "")
    val password by authVM.password.observeAsState(initial = "")
    val isAvailable = password.isNotEmpty() && username.isNotEmpty()

    Column(
        modifier = Modifier
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(50.dp))
        WelcomeText("Inicia sesión en tu cuenta")
        Spacer(modifier = Modifier.height(25.dp))
        UsernameTextField(
            text = username,
            onValueChange = { authVM.onValueChanged(username = it) }
        )
        Spacer(modifier = Modifier.height(15.dp))
        PasswordTextField(
            text = password,
            availableValidation = false,
            isValid = true,
            errorMessages = emptyList(),
            onTextChanged = { authVM.onValueChanged(password = it) }
        )
        Spacer(modifier = Modifier.height(25.dp))

        SubmitButton("Iniciar sesión", onClick = {}, isAvailable)

        Spacer(modifier = Modifier.height(50.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LabelClickable(
                    question = "¿Olvidaste tu contraseña?",
                    action = "Recupérala",
                    onClick = { pushToForgotPassword(navController) }
                )
                LabelClickable(
                    question = "¿No tienes una cuenta?",
                    action = "Regístrate",
                    onClick = { pushToSignUp(navController) }
                )
            }
        }
    }
}

private fun pushToForgotPassword(navController: NavController) {
    navController.navigate("EmailForgotPasswordFlow")
}

private fun pushToSignUp(navController: NavController) {
    navController.navigate("EmailSignUpFlow")
}