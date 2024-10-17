package com.example.tasker.ui.auth.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.tasker.ui.auth.viewmodel.AuthViewModel

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
    onTextChanged: (String) -> Unit,
    onClick: () -> Unit = {},
) {
    val colorScheme = MaterialTheme.colorScheme
    val isPasswordVisible = remember { mutableStateOf(false) }
    var visualTransformation by remember {
        mutableStateOf<VisualTransformation>(
            PasswordVisualTransformation()
        )
    }
    var btnIcon by remember {
        mutableStateOf(
            Icons.Outlined.VisibilityOff
        )
    }

    val indicatorColor = if (isValid) colorScheme.primary else colorScheme.error
    val unfocusedIndicatorColor = if (isValid) colorScheme.outline else colorScheme.error
    val labelColor = if (isValid) colorScheme.primary else colorScheme.error
    val placeholderColor = colorScheme.onSurface.copy(alpha = 0.6f)
    val containerColor = colorScheme.surface

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = if (availableValidation) indicatorColor else colorScheme.primary,
        unfocusedIndicatorColor = if (availableValidation) unfocusedIndicatorColor else colorScheme.outline,
        cursorColor = colorScheme.primary,
        focusedContainerColor = containerColor,
        focusedLabelColor = if (availableValidation) labelColor else colorScheme.primary,
        unfocusedLabelColor = colorScheme.onSurface,
        unfocusedPlaceholderColor = placeholderColor,
        unfocusedContainerColor = containerColor
    )

    Column {
        Text(
            text = label,
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = text,
            onValueChange = {
                onTextChanged(it)
            },
            trailingIcon = {
                VisibilityButton(btnIcon, onClick = {
                    val (transformation, icon) = buttonClick(isPasswordVisible)
                    visualTransformation = transformation
                    btnIcon = icon
                })
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text = "Escribe tu contraseña",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            isError = if (availableValidation) errorMessages.isNotEmpty() else false,
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            colors = colors,
            visualTransformation = visualTransformation,
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

private fun buttonClick(
    isPasswordVisible: MutableState<Boolean>
): Pair<VisualTransformation, ImageVector> {
    isPasswordVisible.value = !isPasswordVisible.value
    return if (isPasswordVisible.value) {
        VisualTransformation.None to Icons.Outlined.Visibility
    } else {
        PasswordVisualTransformation() to Icons.Outlined.VisibilityOff
    }
}