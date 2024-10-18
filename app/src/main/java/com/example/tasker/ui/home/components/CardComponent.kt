package com.example.tasker.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.tasker.R
import com.example.tasker.data.models.Status
import com.example.tasker.ui.theme.Blue
import com.example.tasker.ui.theme.CardProperties
import com.example.tasker.ui.theme.Red


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CardComponent(
    id: String = "uwu",
    title: String = "Default title",
    status: Status = Status.DONE,
) {
    val context = LocalContext.current

    val cardProperties = remember(status) {
        when (status) {
            Status.DONE -> CardProperties(
                statusBackgroundColor = Color(ContextCompat.getColor(context, R.color.mint)),
                statusIconColor = Color(ContextCompat.getColor(context, R.color.green)),
                statusIcon = R.drawable.done_icon
            )

            Status.TODO -> CardProperties(
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
            .height(215.dp)
            .padding(horizontal = 15.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Card(
                    colors = CardDefaults.cardColors(cardProperties.statusBackgroundColor),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(50.dp, 50.dp),
                    shape = CircleShape,
                    border = BorderStroke(1.dp, cardProperties.statusIconColor)
                )
                {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Image(
                            modifier = Modifier.size(25.dp, 25.dp),
                            painter = painterResource(id = cardProperties.statusIcon),
                            contentDescription = stringResource(id = R.string.dog_content_description),
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                                cardProperties.statusIconColor
                            )
                        )
                    }
                }

                Column {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Creado por: Levantatedelsueloniña",
                        modifier = Modifier
                            .padding(horizontal = 5.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    GraphicComponent(0.6f) // Todo: return task progress get this value from params
                    Spacer(modifier = Modifier.height(5.dp))


                    PriorityComponent()

                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Close",
                        tint = cardProperties.statusIconColor
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "Expira: \nMartes 12 de octubre, 2024",
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelSmall
                )
                SharingWithComponent()
            }

        }

    }
}

@Composable
fun SharingWithComponent() {
    ProfileComponent(
        onClick = {}
    )
}

@Composable
fun PriorityComponent(priority: Priority = Priority.HIGH) {
    Card(
        modifier = Modifier
            .width(40.dp)
            .height(15.dp)
            .padding(start = 5.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (priority) {
                    Priority.LOW -> "Baja"
                    Priority.MEDIUM -> "Media"
                    Priority.HIGH -> "Alta"
                },
                color = when (priority) {
                    Priority.LOW -> Blue
                    Priority.MEDIUM -> Color.Yellow
                    Priority.HIGH -> Red
                },
                fontSize = 8.sp
            )
        }
    }

}

@Composable
fun GraphicComponent(percentage: Float = 1f) {

    Card(
        modifier = Modifier
            .width(100.dp)
            .height(5.dp)
            .padding(start = 5.dp),
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(percentage)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {

        }
    }
}

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}