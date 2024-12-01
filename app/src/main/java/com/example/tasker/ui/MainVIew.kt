package com.example.tasker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasker.data.controller.Routes
import com.example.tasker.ui.commons.components.NavigationBarComponent
import com.example.tasker.ui.home.view.HomeScreen
import com.example.tasker.ui.theme.TaskerTheme

@Composable
fun MainView(navController: NavHostController) {
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()) {

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.weight(1f)
        ) {
            composable(Routes.HOME) { HomeScreen() }
//            composable(Routes.FRIENDS) { MapsScreen() }
//            composable(Routes.ARCHIVED) { MapsScreen() }

        }

        NavigationBarComponent(navController = navController)
    }
}

@Preview
@Composable
fun PreView() {
    val context = LocalContext.current
    val a = NavHostController(context)
    TaskerTheme {
        MainView(a)
    }
}