package com.example.tasker.ui.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tasker.R

@Composable
fun WelcomeText(
    text: String,
    title: String? = "¡Bienvenido!",
    addPhotoButtonEnable: Boolean = false,
    onClickPhoto: (() -> Unit)? = null,
    imageUri: String? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title!!,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            if (!addPhotoButtonEnable) {
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "Icono de App",
                    modifier = Modifier
                        .height(175.dp)
                        .width(175.dp)
                )
                return
            }
            AddPhotoButton(
                imageUri = imageUri
            ) {
                onClickPhoto?.let { it() }
            }
        }
    }
}