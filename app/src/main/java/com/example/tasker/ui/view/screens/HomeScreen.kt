package com.example.tasker.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.data.models.Status
import com.example.tasker.ui.view.components.AddButtonComponent
import com.example.tasker.ui.view.components.CardComponent
import com.example.tasker.ui.view.components.ToolBarComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { },
        floatingActionButton = { AddButtonComponent()},
    ){
        ToolBarComponent()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            CardComponent("tarea1", title = "tarea 1", Status.TODO)
            CardComponent("tarea2", title = "tarea 2", Status.IN_PROGRESS)
            CardComponent("tarea3", title = "tarea 3", Status.DONE)
            CardComponent("tarea4", title = "tarea 4", Status.IN_PROGRESS)
            CardComponent("tarea5", title = "tarea 5", Status.TODO)
            CardComponent("tarea6", title = "tarea 6", Status.IN_PROGRESS)
            CardComponent("tarea7", title = "tarea 7", Status.DONE)
            CardComponent("tarea8", title = "tarea 8", Status.DONE)
            CardComponent("tarea1", title = "tarea 1", Status.TODO)
            CardComponent("tarea2", title = "tarea 2", Status.IN_PROGRESS)
            CardComponent("tarea3", title = "tarea 3", Status.DONE)
            CardComponent("tarea4", title = "tarea 4", Status.IN_PROGRESS)
            CardComponent("tarea5", title = "tarea 5", Status.TODO)
            CardComponent("tarea6", title = "tarea 6", Status.IN_PROGRESS)
            CardComponent("tarea7", title = "tarea 7", Status.DONE)
            CardComponent("tarea8", title = "tarea 8", Status.DONE)


        }

    }
}
