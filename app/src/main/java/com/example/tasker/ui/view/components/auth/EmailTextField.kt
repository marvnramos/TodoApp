package com.example.tasker.ui.view.components.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.tasker.ui.view.theme.GreyTitle
import com.example.tasker.ui.view.theme.GreyTitleBorder
import com.example.tasker.ui.view.theme.Orange
import com.example.tasker.ui.view_model.AuthViewModel


@Composable
fun EmailTextField(
    text: String,
    isValid: Boolean,
    errorMessage: String?,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        textStyle = TextStyle.Default,
        placeholder = { Text(text = "mail@ejemplo.com") },
        isError = !isValid,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = if (isValid) Orange else Color.Red,
            unfocusedIndicatorColor = if (isValid) GreyTitleBorder else Color.Red,
            cursorColor = Orange,
            focusedContainerColor = GreyTitle,
            focusedLabelColor = if (isValid) Orange else Color.Red,
            unfocusedLabelColor = Color.Black,
            unfocusedPlaceholderColor = Color.LightGray,
            unfocusedContainerColor = GreyTitle
        ),
        supportingText = {
            errorMessage?.let {
                Text(text = it, color = Color.Red)
            }
        }
    )
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