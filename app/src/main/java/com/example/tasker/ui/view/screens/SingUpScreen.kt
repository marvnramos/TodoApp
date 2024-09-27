package com.example.tasker.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.lifecycle.MutableLiveData
import com.example.tasker.ui.view.components.auth.EmailTextField
import com.example.tasker.ui.view.components.auth.LabelClickable
import com.example.tasker.ui.view.components.auth.SubmitButton
import com.example.tasker.ui.view.components.auth.WelcomeText
import com.example.tasker.ui.view.theme.TaskerTheme
import com.example.tasker.ui.view_model.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(authVM: AuthViewModel = AuthViewModel()) {
    val email by authVM.email.observeAsState(initial = "")
    val isValidEmail = if (email.isNotEmpty()) authVM.isEmailValid(MutableLiveData(email)) else true

    val errorMessage = if (!isValidEmail) "Dirección de correo inválida" else null

    val isError = errorMessage != null
    val isAvailable = email.isNotEmpty() && !isError

    val colorScheme = MaterialTheme.colorScheme
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { /* Acción de retroceso */ }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Menú Icono",
                            tint = colorScheme.onSurface
                        )
                    }
                }
            )
        },
        floatingActionButton = { /* Aquí puedes agregar un FAB si lo necesitas */ }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 35.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            WelcomeText("Registra una nueva cuenta")
            Spacer(modifier = Modifier.height(25.dp))
            EmailTextField(
                text = email,
                isValid = isValidEmail,
                errorMessage = errorMessage,
                onTextChanged = { authVM.onValueChanged(email = it) }
            )

            Spacer(modifier = Modifier.height(25.dp))
            SubmitButton(text = "Siguiente", onClick = { /*TODO*/ }, enabled = isAvailable)

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LabelClickable(
                    question = "¿Ya tienes una cuenta?",
                    action = "Inicia sesión",
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    TaskerTheme {
        SignUpScreen()
    }
}
