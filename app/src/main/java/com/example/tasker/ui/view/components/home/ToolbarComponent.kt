package com.example.tasker.ui.view.components.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.core.content.ContextCompat
import com.example.tasker.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ToolBarComponent(){
    val context = LocalContext.current

    val menuExpanded = remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color(ContextCompat.getColor(context, R.color.orange)),
                ),
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.celebration),
                        contentDescription = "Menu Icon",
                        tint = Color(ContextCompat.getColor(context, R.color.orange))
                    )
                },
                title = { /* there's not title */ },
                actions = {
                    IconButton(onClick = { menuExpanded.value = true }) {
                        Icon(
                            Icons.Rounded.MoreVert,
                            contentDescription = "More Options",
                            tint = Color(ContextCompat.getColor(context,R.color.orange))
                        )
                    }
                    DropdownMenu(
                        expanded = menuExpanded.value,
                        onDismissRequest = { menuExpanded.value = false },
                        modifier = Modifier.background(Color(ContextCompat.getColor(context, R.color.white)))
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
   ){

    }
}