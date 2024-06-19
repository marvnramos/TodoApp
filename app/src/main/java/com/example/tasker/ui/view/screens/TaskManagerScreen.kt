package com.example.tasker.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.tasker.R
import com.example.tasker.ui.view.components.AddButtonComponent
import com.example.tasker.ui.view.components.TaskManagerBarComponent
import com.example.tasker.ui.view.components.TextFieldComponent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun TaskManagerScreen(){
    val context = LocalContext.current
    Scaffold(
        topBar = { },
        floatingActionButton = { AddButtonComponent() },
    ){
        TaskManagerBarComponent()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 75.dp)
//                .verticalScroll(rememberScrollState())
                .background(Color(ContextCompat.getColor(context, R.color.white))),

            ) {
            TextFieldComponent(false)
        }
    }
}