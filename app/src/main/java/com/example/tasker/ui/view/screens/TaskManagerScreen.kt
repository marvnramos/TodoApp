package com.example.tasker.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.R
import com.example.tasker.data.models.Status
import com.example.tasker.ui.view.components.taskmanager_components.DescriptionFieldComponent
import com.example.tasker.ui.view.components.taskmanager_components.IconComponent
import com.example.tasker.ui.view.components.taskmanager_components.StatusComponent
import com.example.tasker.ui.view.components.taskmanager_components.TaskManagerBarComponent
import com.example.tasker.ui.view.components.taskmanager_components.TitleFieldComponent
import com.example.tasker.ui.view_model.TaskManagerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun TaskManagerScreen( viewModel: TaskManagerViewModel = TaskManagerViewModel()) {
    val title: String by viewModel.title.observeAsState(initial = "")
    val description: String by viewModel.description.observeAsState(initial = "")
    val selectedStatus = remember { mutableStateOf<Status?>(null) }

//    val robotoFamily = FontFamily(
//        Font(R.font.roboto_regular, FontWeight.Normal),
//        Font(R.font.roboto_light, FontWeight.Light),
//        Font(R.font.roboto_italic, FontWeight.Normal, FontStyle.Italic),
//        Font(R.font.roboto_medium, FontWeight.Medium),
//        Font(R.font.roboto_mediumitalic, FontWeight.Medium, FontStyle.Italic),
//        Font(R.font.roboto_bold, FontWeight.Bold),
//        Font(R.font.roboto_bolditalic, FontWeight.Bold, FontStyle.Italic)
//    )
    Scaffold(
        topBar = { },
    ) {
        TaskManagerBarComponent(title, description, selectedStatus)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 75.dp)
        ) {
            Text(
                text = "Título",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
//                fontFamily = robotoFamily,
//                fontWeight = FontWeight.Normal
            )
            TitleFieldComponent(
                text = title,
//                onTitleChanged = {
//                    viewModel.onTextFieldChanged(title, it)
//                }
            ){
                viewModel.onTextFieldChanged(it, description)
            }

            Text(
                text = "Descripción",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
//                fontFamily = robotoFamily,
//                fontWeight = FontWeight.Normal
            )

            DescriptionFieldComponent(text = description){
                viewModel.onTextFieldChanged(title, it)
            }

            Text(
                text = "Estado",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
//                fontFamily = robotoFamily,
//                fontWeight = FontWeight.Normal
            )
            Row (
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(10.dp)
            ){
                StatusComponent(
                    status = Status.IN_PROGRESS,
                    isSelected = selectedStatus.value == Status.IN_PROGRESS,
                    onStatusSelected = { selectedStatus.value = Status.IN_PROGRESS }
                )
                Spacer(modifier = Modifier.width(5.dp))
                StatusComponent(
                    status = Status.DONE,
                    isSelected = selectedStatus.value == Status.DONE,
                    onStatusSelected = { selectedStatus.value = Status.DONE }
                )
                Spacer(modifier = Modifier.width(5.dp))
                StatusComponent(
                    status = Status.TODO,
                    isSelected = selectedStatus.value == Status.TODO,
                    onStatusSelected = { selectedStatus.value = Status.TODO }
                )
            }
            Text(
                text = "Icono",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
//                fontFamily = robotoFamily,
//                fontWeight = FontWeight.Normal
            )
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ){
                IconComponent(R.drawable.book)
                IconComponent(R.drawable.coffe)
                IconComponent(R.drawable.clock)
                IconComponent(R.drawable.pc)
                IconComponent(R.drawable.thinking)
                IconComponent(R.drawable.workout)
            }
        }
    }
}