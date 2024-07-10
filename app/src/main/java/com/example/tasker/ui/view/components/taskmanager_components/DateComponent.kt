package com.example.tasker.ui.view.components.taskmanager_components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.tasker.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DatePickerComponent() {
    val dateState = rememberDatePickerState()
    val context = LocalContext.current

    DatePicker(
        state = dateState,
        colors = DatePickerDefaults.colors(
            selectedDayContainerColor = Color(ContextCompat.getColor(context, R.color.blue)),
            todayDateBorderColor = Color(ContextCompat.getColor(context, R.color.blue)),
            todayContentColor = Color(ContextCompat.getColor(context, R.color.blue)),
            selectedYearContainerColor = Color(ContextCompat.getColor(context, R.color.blue)),
            currentYearContentColor = Color(ContextCompat.getColor(context, R.color.blue))
            )
    )
}
