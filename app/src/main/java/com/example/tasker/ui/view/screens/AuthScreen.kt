package com.example.tasker.ui.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.tasker.R
import com.example.tasker.ui.view.components.auth.EmailTextField
import com.example.tasker.ui.view.components.auth.LabelClickable
import com.example.tasker.ui.view.components.auth.PasswordTextField
import com.example.tasker.ui.view.components.auth.SubmitButton
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@Composable
fun AuthScreen(authVM: AuthViewModel = AuthViewModel()) {
    val email by authVM.email.observeAsState(initial = "")
    val password by authVM.password.observeAsState(initial = "")

    val isValidEmail = if (email.isNotEmpty()) authVM.isEmailValid(MutableLiveData(email)) else true
    val isAvailable = password.isNotEmpty() && isValidEmail && email.isNotEmpty()
    val errorMessage = if (!isValidEmail) "Dirección de correo invalida" else null


    Column(
        modifier = Modifier
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(modifier = Modifier.height(50.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "¡Bienvenido!",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Inicia sesión en tu cuenta",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "Icono de App",
                modifier = Modifier
                    .height(175.dp)
                    .width(175.dp)
            )
        }

        EmailTextField(
            text = email,
            isValid = isValidEmail,
            errorMessage = errorMessage,
            onTextChanged = { authVM.onValueChanged(email = it) }
        ) // username uu
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
                modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LabelClickable(
                    question = "¿Olvidaste tu contraseña?",
                    action = "Recupérala",
                    onClick = {}
                )
                LabelClickable(
                    question = "¿No tienes una cuenta?",
                    action = "Regístrate",
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    TaskerTheme {
        AuthScreen()
    }
}
