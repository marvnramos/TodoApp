package com.example.tasker.ui.view.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.tasker.ui.view.theme.Red
import com.example.tasker.ui.view_model.AuthViewModel


@Composable
fun EmailTextField(
    text: String,
    isValid: Boolean,
    errorMessage: String?,
    onTextChanged: (String) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    val indicatorColor = if (isValid) colorScheme.primary else colorScheme.error
    val unfocusedIndicatorColor = if (isValid) colorScheme.outline else colorScheme.error
    val labelColor = if (isValid) colorScheme.primary else colorScheme.error
    val placeholderColor = colorScheme.onSurface.copy(alpha = 0.6f)
    val containerColor = colorScheme.surface

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = indicatorColor,
        unfocusedIndicatorColor = unfocusedIndicatorColor,
        cursorColor = colorScheme.primary,
        focusedContainerColor = containerColor,
        focusedLabelColor = labelColor,
        unfocusedLabelColor = colorScheme.onSurface,
        unfocusedPlaceholderColor = placeholderColor,
        unfocusedContainerColor = containerColor
    )
    Column {
        Text(
            text = "Correo",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = text,
            onValueChange = { onTextChanged(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = {
                Text(
                    text = "mail@ejemplo.com",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            isError = !isValid,
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(8.dp),
            colors = colors,
            supportingText = {
                errorMessage?.let {
                    Text(text = it, color = Red)
                }
            }
        )
    }
}

@Preview
@Composable
fun EmailFieldContainer(authVM: AuthViewModel = AuthViewModel()) {
    val email by authVM.email.observeAsState(initial = "")
    val isValidEmail = if (email.isNotEmpty()) authVM.isEmailValid(MutableLiveData(email)) else true

    val errorMessage = if (!isValidEmail) "Dirección de correo invalida" else null

    EmailTextField(
        text = email,
        isValid = isValidEmail,
        errorMessage = errorMessage,
        onTextChanged = { authVM.onValueChanged(email = it) }
    )
}