package com.example.tasker.ui.view.components.taskmanager_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Preview
@Composable
fun IconComponent(imageId: Int = R.drawable.coffe){
    var selected by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                Color(ContextCompat.getColor(LocalContext.current, R.color.orangeSoft))
            } else {
                Color(ContextCompat.getColor(LocalContext.current, R.color.white))
            }
        ),
        modifier = Modifier.then(
            if(selected){
                Modifier
                    .size(50.dp)
                    .clickable { selected = !selected }

            }else{
                Modifier
                    .size(50.dp)
                    .clickable { selected = !selected }
            }
        ),

        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                        modifier = Modifier.size(25.dp, 25.dp),
                        painter = painterResource(id = imageId),
                        contentDescription = stringResource(id = R.string.dog_content_description)
                    )
        }
    }
}