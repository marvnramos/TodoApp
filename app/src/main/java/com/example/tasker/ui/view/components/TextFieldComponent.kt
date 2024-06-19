package com.example.tasker.ui.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
@Preview
@Composable
fun TextFieldComponent( lengthIndicator: Boolean = true){
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    val brush = Brush.linearGradient(colors = rainbowColors)
    var placeholdertext = "Escribe un título"

    OutlinedTextField(
        value = text,
        onValueChange = {
            if(!lengthIndicator){
                text = it
                return@OutlinedTextField
            }

            if(it.length <= 25){
                text = it
            }},
        modifier = if (!lengthIndicator) {
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .height(150.dp)
        } else {
            Modifier.fillMaxWidth()
        },
        textStyle = TextStyle(brush = brush),

        placeholder = {
            if(!lengthIndicator){
                placeholdertext = "Escribe una descripción"
            }
            Text(text=placeholdertext)},

        supportingText = {
            if(lengthIndicator){
                TextLengthIndicator(text)
            }
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