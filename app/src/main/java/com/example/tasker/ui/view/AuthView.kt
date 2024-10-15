package com.example.tasker.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.view.screens.AuthScreen
import com.example.tasker.ui.view.screens.EmailScreen
import com.example.tasker.ui.view.screens.SignUpEmailScreen
import com.example.tasker.ui.view.screens.SingUpTokenScreen
import com.example.tasker.ui.view.theme.TaskerTheme

@Composable
fun AuthView() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavHost(
            navController = navController,
            startDestination = "Login",
            modifier = Modifier.weight(1f)
        ) {
            composable("Login") {
                AuthScreen(navController)
            }
            composable("EmailForgotPasswordFlow") {
                EmailScreen(navController)
            }
            composable("EmailSignUpFlow") {
                SignUpEmailScreen(navController)
            }
            composable("TokenSignUpFlow") {
                SingUpTokenScreen(navController)
            }
        }
    }
}

@Preview
@Composable
fun AuthViewPreview() {
    TaskerTheme {
        AuthView()
    }
}