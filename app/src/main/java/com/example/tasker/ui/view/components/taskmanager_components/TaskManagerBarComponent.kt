package com.example.tasker.ui.view.components.taskmanager_components

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.tasker.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun TaskManagerBarComponent(title: String, description: String){
    val context = LocalContext.current

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(ContextCompat.getColor(context, R.color.white)),
                    titleContentColor = Color(ContextCompat.getColor(context, R.color.orange)),
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "go back!", Toast.LENGTH_SHORT).show()
                    /*TODO*/ }) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = "",
                            tint = Color(ContextCompat.getColor(context, R.color.orange))
                        )
                    }
                },
                title = { /* there's not title */ },
                actions = {
                    IconButton(
                        onClick = {
                            Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .background(
                                Color(ContextCompat.getColor(context, R.color.darkGrey)),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                BorderStroke(1.dp, Color.DarkGray),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .size(30.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = stringResource(id = R.string.dog_content_description),
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)

                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    IconButton(
                        onClick = {
                                  Toast.makeText(context, "saved!", Toast.LENGTH_SHORT).show()
                                  },
                        modifier = Modifier
                            .background(
                                Color(ContextCompat.getColor(context, R.color.lightBlue)),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                BorderStroke(
                                    1.dp,
                                    Color(ContextCompat.getColor(context, R.color.blue))
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .size(30.dp)
                        ) {
                        Icon(
                            painter = painterResource(id = R.drawable.save_icon),
                            contentDescription = "",
                            tint = Color(ContextCompat.getColor(context,R.color.blue)),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                },
            )
        }
    ){

    }
}