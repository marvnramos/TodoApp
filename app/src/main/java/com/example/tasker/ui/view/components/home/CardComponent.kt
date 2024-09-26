package com.example.tasker.ui.view.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.tasker.R
import com.example.tasker.data.models.Status
import com.example.tasker.ui.view.theme.CardProperties


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CardComponent(
    id: String = "uwu",
    title: String = "Default title",
    status: Status = Status.DONE,
    icon : Painter = painterResource(id = R.drawable.book)
){
    val context = LocalContext.current

    val cardProperties = remember(status){
        when(status){
            Status.DONE -> CardProperties(
                statusBackgroundColor = Color(ContextCompat.getColor(context, R.color.mint)),
                statusIconColor = Color(ContextCompat.getColor(context, R.color.green)),
                statusIcon = R.drawable.done_icon
            )
            Status.TODO ->CardProperties(
                statusBackgroundColor = Color(ContextCompat.getColor(context, R.color.pink)),
                statusIconColor = Color(ContextCompat.getColor(context, R.color.red)),
                statusIcon = R.drawable.todo_icon
            )
            Status.IN_PROGRESS -> CardProperties(
                statusBackgroundColor = Color(ContextCompat.getColor(context, R.color.lightYellow)),
                statusIconColor = Color(ContextCompat.getColor(context, R.color.cream)),
                statusIcon = R.drawable.in_progress_icon
            )
        }
    }

    Card(
        onClick = { println(id) }, // Todo: return task id for show its content
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp),
        colors = CardDefaults.cardColors(cardProperties.statusBackgroundColor),
        ){

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(ContextCompat.getColor(context, R.color.white))),
                modifier = Modifier
                    .padding(5.dp)
                    .size(50.dp, 50.dp),
            )
            {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Image(
                        modifier = Modifier.size(25.dp,25.dp),
                        painter = icon,
                        contentDescription = stringResource(id = R.string.dog_content_description)
                    )
                }
            }
            Text(text = title,
                modifier = Modifier
                    .padding(5.dp),
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.weight(1f))
            Card(
                colors = CardDefaults.cardColors(cardProperties.statusIconColor),
                modifier = Modifier
                    .padding(5.dp)
                    .size(50.dp, 50.dp),
            )
            {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Image(
                        modifier = Modifier.size(25.dp,25.dp),
                        painter = painterResource(id = cardProperties.statusIcon),
                        contentDescription = stringResource(id = R.string.dog_content_description)
                    )
                }
            }
        }
    }
}