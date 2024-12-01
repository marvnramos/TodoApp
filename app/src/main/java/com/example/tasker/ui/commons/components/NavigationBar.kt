package com.example.tasker.ui.commons.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.tasker.data.controller.Routes

@Composable
fun NavigationBarComponent(navController: NavHostController) {
    var selectedView by remember { mutableIntStateOf(1) }
    val views =
        listOf("Home", "Maps", "Favorites")

    androidx.compose.material3.NavigationBar {
        views.forEachIndexed { index, view ->
            NavigationBarItem(
                icon = {
                    when (index) {
                        0 -> Icon(Icons.Filled.Inventory2, contentDescription = view)
                        1 -> Icon(Icons.Filled.Home, contentDescription = view)
                        else -> Icon(Icons.Filled.GroupAdd, contentDescription = view)
                    }
                },
                selected = selectedView == index,
                onClick = {
                    selectedView = index
                    when (index) {
                        0 -> navController.navigate(Routes.ARCHIVED)
                        1 -> navController.navigate(Routes.HOME)
                        2 -> navController.navigate(Routes.FRIENDS)
                    }
                }
            )
        }
    }
}