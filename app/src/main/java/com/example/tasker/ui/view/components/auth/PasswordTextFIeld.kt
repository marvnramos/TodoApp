package com.example.tasker.ui.view.components.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.tasker.ui.view.theme.GreyTitle
import com.example.tasker.ui.view.theme.GreyTitleBorder
import com.example.tasker.ui.view.theme.Orange
import com.example.tasker.ui.view_model.AuthViewModel

@Preview
@Composable
fun PasswordTextFieldPreview(viewModel: AuthViewModel = AuthViewModel()) {
    val password by viewModel.password.observeAsState(initial = "")
    val context = LocalContext.current

    var errorMessages by remember { mutableStateOf<List<String>>(emptyList()) }
    val isValid = if (password.isNotEmpty()) {
        errorMessages = viewModel.isPasswordValid(MutableLiveData(password))
        errorMessages.isEmpty()
    } else {
        errorMessages = emptyList()
        true
    }

    val confirmedPassword by viewModel.confirmedPassword.observeAsState(initial = "")
    var comparePasswordErrorMessages by remember { mutableStateOf<List<String>>(emptyList()) }

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



    Column {
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

        val isButtonEnabled =
            isValid && samePassword && password.isNotEmpty() && confirmedPassword.isNotEmpty()
        Button(
            onClick = {
                Toast.makeText(context, "Password Submitted", Toast.LENGTH_SHORT).show()
            },
            enabled = isButtonEnabled
        ) {
            Text(text = "Submit")
        }

    }
}

@Composable
fun PasswordTextField(
    label: String = "Contraseña",
    text: String,
    availableValidation: Boolean,
    isValid: Boolean,
    errorMessages: List<String>,
    onTextChanged: (String) -> Unit
) {
    val indicatorColor = if (isValid) Orange else Color.Red
    val unfocusedIndicatorColor = if (isValid) GreyTitleBorder else Color.Red
    val labelColor = if (isValid) Orange else Color.Red

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = if (availableValidation) indicatorColor else Orange,
        unfocusedIndicatorColor = if (availableValidation) unfocusedIndicatorColor else GreyTitleBorder,
        cursorColor = Orange,
        focusedContainerColor = GreyTitle,
        focusedLabelColor = if (availableValidation) labelColor else Orange,
        unfocusedLabelColor = Color.Black,
        unfocusedPlaceholderColor = Color.LightGray,
        unfocusedContainerColor = GreyTitle
    )

    Column {
        Text(
            text = label,
            modifier = Modifier.padding(start = 10.dp),
            style = TextStyle.Default
        )

        OutlinedTextField(
            value = text,
            onValueChange = {
                onTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textStyle = TextStyle.Default,
            placeholder = { Text(text = "Escribe tu contraseña") },
            isError = if (availableValidation) errorMessages.isNotEmpty() else false,
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            colors = colors,
            visualTransformation = PasswordVisualTransformation(),
            supportingText = {
                if (errorMessages.isNotEmpty() && availableValidation) {
                    Column {
                        errorMessages.forEach { errorMessage ->
                            Text(text = errorMessage, color = Color.Red)
                        }
                    }
                }
            }
        )
    }
}
