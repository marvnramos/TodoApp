package com.example.tasker.ui.home.components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.tasker.R
import com.example.tasker.ui.theme.TaskerTheme
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.tasker.ui.theme.Blue
import com.example.tasker.ui.theme.Red


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarComponent() {
    val context = LocalContext.current

    val menuExpanded = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.height(135.dp),
                navigationIcon = {
                    ProfileComponent()
                },
                title = {
                    Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                        Text(
                            text = "¡Buenos días, ",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "John Doe! \uD83D\uDC4B",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold
                        )
                    }
                },
                actions = {
                    Row(Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { menuExpanded.value = true }) {
                            Icon(
                                Icons.Rounded.FilterAlt,
                                contentDescription = "Filters",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Rounded.Notifications,
                                contentDescription = "Notifications",
                                tint = MaterialTheme.colorScheme.onSurface
                            )

                        }
                    }

                    DropdownMenu(
                        expanded = menuExpanded.value,
                        onDismissRequest = { menuExpanded.value = false },
                        modifier = Modifier.background(
                            MaterialTheme.colorScheme.surface
                        ).width(200.dp),
                        offset = DpOffset(x = 5.dp, y = 5.dp),
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Por hacer") },
                            onClick = {
                                menuExpanded.value = false
                                Toast.makeText(context, "clicked 1", Toast.LENGTH_SHORT).show()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "En progreso") },
                            onClick = {
                                menuExpanded.value = false
                                Toast.makeText(context, "clicked 2", Toast.LENGTH_SHORT).show()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Hecho") },
                            onClick = {
                                menuExpanded.value = false
                                Toast.makeText(context, "clicked 3", Toast.LENGTH_SHORT).show()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Todo") },
                            onClick = {
                                menuExpanded.value = false
                                Toast.makeText(context, "clicked 3", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            )
        },
    ) {

    }
}

@Preview
@Composable
fun ToolBarComponentPreview() {
    TaskerTheme {
        ToolBarComponent()
    }
}

data class ComponentParams(
    val onProfileClick: () -> Unit,
    val onFilterClick: () -> Unit,
    val onNotificationClick: () -> Unit,
)