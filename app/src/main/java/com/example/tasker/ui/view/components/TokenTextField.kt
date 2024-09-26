package com.example.tasker.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.ui.view.theme.GreyTitle
import com.example.tasker.ui.view.theme.GreyTitleBorder
import com.example.tasker.ui.view.theme.Orange
import com.example.tasker.ui.view_model.AuthViewModel

@Preview
@Composable
fun TokenTextFieldPreview(authVM: AuthViewModel = AuthViewModel()) {
    val token by authVM.token.observeAsState(initial = "")

    TokenTextField(
        text = token,
        onTextChanged = {
            authVM.onValueChanged(token = it)
        }
    )
}

@Composable
fun TokenTextField(
    text: String,
    onTextChanged: (String) -> Unit
) {
    val keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    )

    val isValid = text.length == 6
    val errorIndicator = text.isNotEmpty() && !isValid

    val colors = TextFieldDefaults.colors(
        focusedIndicatorColor = if (!errorIndicator) Orange else Color.Red,
        unfocusedIndicatorColor = if (!errorIndicator) GreyTitleBorder else Color.Red,
        cursorColor = Orange,
        focusedContainerColor = GreyTitle,
        focusedLabelColor = if (!errorIndicator) Orange else Color.Red,
        unfocusedLabelColor = Color.Black,
        unfocusedPlaceholderColor = Color.LightGray,
        unfocusedContainerColor = GreyTitle
    )

    Column {
        Text(
            text = "Código",
            modifier = Modifier.padding(start = 10.dp),
            style = TextStyle.Default
        )

        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.isValidNumber() && it.length <= 6) onTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textStyle = TextStyle.Default,
            placeholder = { Text(text = "000000") },
            isError = errorIndicator,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = keyboardOptions,
            shape = RoundedCornerShape(8.dp),
            colors = colors,
            supportingText = {
                if (errorIndicator) {
                    Text(text = "El código debe tener 6 dígitos", color = Color.Red)
                }
            }
        )
    }
}

fun String.isValidNumber(): Boolean {
    val numberRegex = Regex("^\\d*\$")
    return this.matches(numberRegex)
}
