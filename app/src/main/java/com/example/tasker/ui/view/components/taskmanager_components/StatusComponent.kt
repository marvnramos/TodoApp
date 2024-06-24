package com.example.tasker.ui.view.components.taskmanager_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.tasker.R
import com.example.tasker.data.models.Status
import com.example.tasker.data.models.StatusProperties

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StatusComponent(status: Status = Status.IN_PROGRESS){
    val context = LocalContext.current
    var selected by remember { mutableStateOf(false) }
    val statusProperties = remember(status){
        when(status){
            Status.DONE-> StatusProperties(
                statusText = "Hecho",
                statusColor = Color(ContextCompat.getColor(context, R.color.green)),
                statusIcon = R.drawable.done_icon
            )
            Status.TODO -> StatusProperties(
                statusText = "Por hacer",
                statusColor = Color(ContextCompat.getColor(context, R.color.red)),
                statusIcon = R.drawable.todo_icon
            )
            Status.IN_PROGRESS -> StatusProperties(
                statusText = "En progreso",
                statusColor = Color(ContextCompat.getColor(context, R.color.cream)),
                statusIcon = R.drawable.in_progress_icon
            )
        }
    }

    FilterChip(
        selected = selected ,
        onClick = { selected = !selected },
        label = { Text(text = statusProperties.statusText) },
        modifier = Modifier.then(
            if(selected){
                Modifier.border(
                    BorderStroke(2.dp, Color(ContextCompat.getColor(context, R.color.darkBlue))),
                    shape = RoundedCornerShape(8.dp)
                )
                    .padding(0.dp)
            }else{
                Modifier.padding(0.dp)
            }
        ),
        leadingIcon = {
            Card(
                colors = CardDefaults.cardColors(containerColor = statusProperties.statusColor),
                modifier = Modifier
                    .padding(top = 9.dp, bottom = 9.dp, start = 0.dp)
                    .size(50.dp, 50.dp),
            )
            {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Image(
                        modifier = Modifier.size(25.dp,25.dp),
                        painter = painterResource(id = statusProperties.statusIcon),
                        contentDescription = stringResource(id = R.string.dog_content_description)
                    )
                }
            }
        },
        trailingIcon = if (selected){
            {
                Box(
                    Modifier.border(
                        BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(1.dp),
                    )
                ){
                    Icon(
                        Icons.Rounded.Check,
                        contentDescription =  "",
                        tint = Color(ContextCompat.getColor(context, R.color.blue))
                    )
                }
            }
        }else{
            null
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color(ContextCompat.getColor(context, R.color.white)),
            selectedContainerColor = Color(ContextCompat.getColor(context, R.color.white))
        )
        )
    }
