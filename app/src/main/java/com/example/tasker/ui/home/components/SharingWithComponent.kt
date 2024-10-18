package com.example.tasker.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.IconButton
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasker.ui.theme.TaskerTheme


@Composable
fun SharingWithComponent() {
    Box(
        modifier = Modifier
            .width(60.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://firebasestorage.googleapis.com/v0/b/todotest-8ceb3.appspot.com/o/profiles%2F1729090620183.jpg?alt=media&token=89bdcc4b-6972-4b42-876c-d2c35c9217c5"),
            contentDescription = "Icono 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .align(Alignment.CenterStart)
        )

        Image(
            painter = rememberAsyncImagePainter(model = "https://firebasestorage.googleapis.com/v0/b/todotest-8ceb3.appspot.com/o/profiles%2F1728073435083.jpg?alt=media&token=0fe744d1-75c2-471e-ab9f-834c15585709"), // Cambia el URL según tus imágenes
            contentDescription = "Icono 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                .align(Alignment.Center)
        )

        IconButton(
            onClick = {},
            enabled = false,
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary)
                .border(1.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                .align(Alignment.CenterEnd)
                .offset(x = 0.dp)
        ) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "Añadir",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Preview
@Composable
fun SharingWithComponentPreview() {
    TaskerTheme {
        SharingWithComponent()
    }
}