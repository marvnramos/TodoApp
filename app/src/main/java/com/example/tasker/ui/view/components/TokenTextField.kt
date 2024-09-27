package com.example.tasker.ui.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
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

//    TokenTextField(
//        text = token,
//        onTextChanged = {
//            authVM.onValueChanged(token = it)
//        }
//    )
}

@Composable
fun TokenTextField(
    text: String,
    isValid: Boolean,
    isError: Boolean,
    onTextChanged: (String) -> Unit
) {
    val keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    )

//    val isValid = text.length == 6
//    val errorIndicator = text.isNotEmpty() && !isValid

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
            text = "Código",
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = text,
            onValueChange = {
                if (it.isValidNumber() && it.length <= 6) onTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textStyle = MaterialTheme.typography.bodyMedium,
            placeholder = { Text(text = "000000", style = MaterialTheme.typography.bodyMedium) },
            isError = isError,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = keyboardOptions,
            shape = RoundedCornerShape(8.dp),
            colors = colors,
            supportingText = {
                if (isError) {
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
