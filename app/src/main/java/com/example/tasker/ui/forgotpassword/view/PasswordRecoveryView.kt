package com.example.tasker.ui.forgotpassword.view

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.view.Routes
import com.example.tasker.ui.auth.components.PasswordTextField
import com.example.tasker.ui.theme.TaskerTheme
import com.example.tasker.ui.auth.viewmodel.AuthViewModel
import com.example.tasker.ui.commons.TemplateView

@Composable
fun PasswordRecoveryView(
    navController: NavHostController,
    authVM: AuthViewModel = AuthViewModel()
) {
    val password by authVM.password.observeAsState(initial = "")
    val confirmedPassword by authVM.confirmedPassword.observeAsState(initial = "")
    var errorMessages by remember { mutableStateOf<List<String>>(emptyList()) }
    var comparePasswordErrorMessages by remember { mutableStateOf<List<String>>(emptyList()) }

    val isValid = if (password.isNotEmpty()) {
        errorMessages = authVM.isPasswordValid(MutableLiveData(password))
        errorMessages.isEmpty()
    } else {
        errorMessages = emptyList()
        true
    }

    val samePassword = if (confirmedPassword.isNotEmpty()) {
        if (!authVM.comparePasswords(
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

    val isAvailable =
        password.isNotEmpty() && confirmedPassword.isNotEmpty() && isValid && samePassword

    val command = SubmitNewPasswordCommand(
        newPassword = password,
        confirmedPassword = confirmedPassword,
        navController = navController
    )
    TemplateView(
        onArrowClick = { navController.popBackStack() },
        onTextClick = { navController.popBackStack() },
        onSubmitClick = { submitNewPassword(command) },
        subWelcomeText = "Escribe tu nueva contraseña",
        textIndicator = null,
        submitText = "Actualizar",
        title = "Recuperar contraseña",
        isAvailable = isAvailable,
        textFieldComponent = {
            PasswordTextField(
                label = "Nueva contraseña",
                text = password,
                availableValidation = true,
                isValid = isValid,
                errorMessages = emptyList(),
                onTextChanged = { authVM.onValueChanged(password = it) }
            )

            PasswordTextField(
                label = "Confirmar contraseña",
                text = confirmedPassword,
                availableValidation = true,
                isValid = samePassword,
                errorMessages = comparePasswordErrorMessages + errorMessages,
                onTextChanged = { authVM.onValueChanged(confirmedPassword = it) }
            )
        },
        loginAvailable = false
    )
}

@Preview
@Composable
fun ForgotPasswordScreenPreview() {
    TaskerTheme {
        PasswordRecoveryView(navController = rememberNavController())
    }
}

private fun submitNewPassword(command: SubmitNewPasswordCommand) {
    val (newPassword, confirmedPassword, navController) = command
    /*
     * TODO: Implement submitNewPassword
     */
    println("Contraseña: $newPassword, Nueva contraseña: $confirmedPassword")
    Toast.makeText(navController.context, "Contraseña actualizada", Toast.LENGTH_SHORT).show()
    navController.navigate(Routes.LOGIN)
}

data class SubmitNewPasswordCommand(
    val newPassword: String, val confirmedPassword: String, val navController: NavHostController
)
