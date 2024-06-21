package com.example.tasker.ui.view.components.home_components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.core.content.ContextCompat
import com.example.tasker.R


@Preview
@Composable
fun AddButtonComponent(){
    val context = LocalContext.current
    FloatingActionButton(
        onClick = { /*TODO: Open 2nd screen*/ },
        containerColor = Color(ContextCompat.getColor(context, R.color.orange)),
        contentColor = Color(ContextCompat.getColor(context, R.color.white)),

    ) {
        Icon(Icons.Filled.Add, contentDescription = "Floating action button.")
    }
}