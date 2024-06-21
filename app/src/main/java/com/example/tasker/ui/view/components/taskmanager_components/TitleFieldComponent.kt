package com.example.tasker.ui.view.components.taskmanager_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.tasker.R

val rainbowColors = listOf(
    Color.Red,
    Color.Yellow,
    Color.Green,
    Color.Blue,
    Color.Magenta
)

@Composable
fun TextLengthIndicator(text: String){
    val context = LocalContext.current
    Text(
        text = "${text.length}/25",
        fontWeight =
        if(text.length == 25) {
            FontWeight.Bold
        } else{
            FontWeight.Normal
        },
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Right,
        color =
        if(text.length == 25){
            Color(ContextCompat.getColor(context, R.color.orange))
        }else  {
            Color.Gray
        }
    )
}

@Composable
fun TitleFieldComponent(
    text: String,
    onTitleChanged: (String) -> Unit
){
    val context = LocalContext.current
//    var text by remember { mutableStateOf("") }

    val brush = Brush.linearGradient(colors = rainbowColors)

    OutlinedTextField(
        value = text,
        onValueChange = {
            if(it.length <= 25){
                onTitleChanged(it)
            }},
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
        textStyle = TextStyle(brush = brush),

        placeholder = {
            Text(text="Escribe un título")},
        keyboardActions = KeyboardActions {

        },
        supportingText = {
            TextLengthIndicator(text)
        },
        maxLines = 6,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(ContextCompat.getColor(context, R.color.orange)),
            unfocusedIndicatorColor = Color(ContextCompat.getColor(context, R.color.grey_title_border)),
            cursorColor = Color(ContextCompat.getColor(context, R.color.orange)),
            focusedContainerColor = Color(ContextCompat.getColor(context, R.color.grey_title)),
            focusedLabelColor = Color(ContextCompat.getColor(context, R.color.orange)),
            unfocusedLabelColor = Color.Black,
            unfocusedPlaceholderColor = Color.LightGray,
            unfocusedContainerColor = Color(ContextCompat.getColor(context, R.color.grey_title))
        )
    )
}