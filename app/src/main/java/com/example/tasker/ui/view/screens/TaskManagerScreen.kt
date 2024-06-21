package com.example.tasker.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasker.ui.view.components.taskmanager_components.DescriptionFieldComponent
import com.example.tasker.ui.view.components.taskmanager_components.TaskManagerBarComponent
import com.example.tasker.ui.view.components.taskmanager_components.TitleFieldComponent
import com.example.tasker.ui.view_model.TaskManagerViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun TaskManagerScreen( viewModel: TaskManagerViewModel = TaskManagerViewModel()) {
    val title: String by viewModel.title.observeAsState(initial = "")
    val description: String by viewModel.description.observeAsState(initial = "")
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
        TaskManagerBarComponent(title, description)
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
        }
    }
}