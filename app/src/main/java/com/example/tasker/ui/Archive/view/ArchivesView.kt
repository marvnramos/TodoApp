package com.example.tasker.ui.Archive.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tasker.data.models.Status
import com.example.tasker.ui.commons.components.WelcomeText
import com.example.tasker.ui.home.components.CardComponent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArchiveScreen(onArrowClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Archivadas",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 15.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
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
    }

}