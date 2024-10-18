package com.example.tasker.ui.home.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.data.models.Status
import com.example.tasker.ui.home.components.AddButtonComponent
import com.example.tasker.ui.home.components.CardComponent
import com.example.tasker.ui.home.components.ComponentParams
import com.example.tasker.ui.home.components.ToolBarComponent
import com.example.tasker.ui.theme.TaskerTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val params = ComponentParams(
        onProfileClick = { },
        onFilterClick = { },
        onNotificationClick = { },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 150.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color.Transparent),

                ) {
                CardComponent("tarea1", title = "tarea 1", Status.TODO)
                CardComponent("tarea2", title = "tarea 2", Status.IN_PROGRESS)
                CardComponent("tarea3", title = "tarea 3", Status.DONE)
                CardComponent("tarea4", title = "tarea 4", Status.IN_PROGRESS)
                CardComponent("tarea5", title = "tarea 5", Status.TODO)
                CardComponent("tarea6", title = "tarea 6", Status.IN_PROGRESS)
                CardComponent("tarea7", title = "tarea 7", Status.DONE)
                CardComponent("tarea6", title = "tarea 6", Status.IN_PROGRESS)
                CardComponent("tarea6", title = "tarea 6", Status.IN_PROGRESS)
                CardComponent("tarea6", title = "tarea 6", Status.IN_PROGRESS)
                CardComponent("tarea6", title = "tarea 6", Status.IN_PROGRESS)
            }
        },
        floatingActionButton = {
            AddButtonComponent { pushToTaskManager() }
        }
    )
    ToolBarComponent(params)
}

@Preview
@Composable
fun HomeScreenPreview() {
    TaskerTheme {
        HomeScreen()
    }
}

private fun pushToTaskManager(){
    println("pushToTaskManager")
}