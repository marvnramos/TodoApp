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

    Column {
        PasswordTextField(
            text = password,
            availableValidation = false,
            isValid = isValid,
            errorMessages = errorMessages,
            onTextChanged = { viewModel.onValueChanged(password = it) }
        )

        Button(
            onClick = {
                Toast.makeText(context, "Password Submitted", Toast.LENGTH_SHORT).show()
            },
            enabled = isValid && password.isNotEmpty()
        ) {
            Text(text = "Submit")
        }
    }
}

@Composable
fun PasswordTextField(
    text: String,
    availableValidation: Boolean,
    isValid: Boolean,
    errorMessages: List<String>,
    onTextChanged: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { newValue ->
                onTextChanged(newValue)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textStyle = TextStyle.Default,
            placeholder = { Text(text = "Escribe tu contraseña") },
            isError = errorMessages.isNotEmpty(),
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            colors = if (availableValidation) {
                TextFieldDefaults.colors(
                    focusedIndicatorColor = if (isValid) Orange else Color.Red,
                    unfocusedIndicatorColor = if (isValid) GreyTitleBorder else Color.Red,
                    cursorColor = Orange,
                    focusedContainerColor = GreyTitle,
                    focusedLabelColor = if (isValid) Orange else Color.Red,
                    unfocusedLabelColor = Color.Black,
                    unfocusedPlaceholderColor = Color.LightGray,
                    unfocusedContainerColor = GreyTitle
                )
            } else {
                TextFieldDefaults.colors(
                    focusedIndicatorColor = Orange,
                    unfocusedIndicatorColor = GreyTitleBorder,
                    cursorColor = Orange,
                    focusedContainerColor = GreyTitle,
                    focusedLabelColor = Orange,
                    unfocusedLabelColor = Color.Black,
                    unfocusedPlaceholderColor = Color.LightGray,
                    unfocusedContainerColor = GreyTitle
                )
            },
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
