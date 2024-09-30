package com.example.tasker.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import com.example.tasker.ui.view.components.auth.LabelClickable
import com.example.tasker.ui.view.components.auth.SubmitButton
import com.example.tasker.ui.view.components.auth.WelcomeText

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SingUpTemplate(
    onArrowClick: () -> Unit,
    onTextClick: () -> Unit,
    onSubmitClick: () -> Unit,
    subWelcomeText: String,
    textIndicator: String?,
    submitText: String,
    isAvailable: Boolean,
    textFieldComponent: @Composable () -> Unit,
    addPhotoButtonEnable: Boolean = false,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onArrowClick() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "Menú Icono",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 35.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween  // Pushes elements apart vertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                WelcomeText(subWelcomeText, addPhotoButtonEnable)
                textIndicator?.let {
                    Text(
                        text = textIndicator,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))
                textFieldComponent()

                Spacer(modifier = Modifier.height(25.dp))
                SubmitButton(text = submitText, onClick = { onSubmitClick() }, enabled = isAvailable)

                Spacer(modifier = Modifier.height(25.dp))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LabelClickable(
                    question = "¿Ya tienes una cuenta?",
                    action = "Inicia sesión",
                    onClick = { onTextClick() }
                )
            }
        }
    }
}
