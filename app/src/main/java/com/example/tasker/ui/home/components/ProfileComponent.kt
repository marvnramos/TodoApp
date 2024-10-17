package com.example.tasker.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileComponent() {
    Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.width(10.dp))
        Card(
            onClick = { },
            modifier = Modifier.size(30.dp),
            elevation = CardDefaults.elevatedCardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = CircleShape,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "https://firebasestorage.googleapis.com/v0/b/todotest-8ceb3.appspot.com/o/profiles%2F1729090620183.jpg?alt=media&token=89bdcc4b-6972-4b42-876c-d2c35c9217c5"),
                contentDescription = "Selected photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
    }
}