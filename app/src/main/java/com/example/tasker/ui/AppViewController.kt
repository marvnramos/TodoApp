package com.example.tasker.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasker.ui.theme.TaskerTheme

import com.example.tasker.ui.auth.view.SingUpView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasker.data.controller.Routes
import com.example.tasker.ui.auth.view.LoginView
import com.example.tasker.ui.forgotpassword.view.EmailView
import com.example.tasker.ui.auth.view.EmailView as EmailAuthView
import com.example.tasker.ui.forgotpassword.view.PasswordRecoveryView
import com.example.tasker.ui.forgotpassword.view.TokenView
import com.example.tasker.ui.auth.view.TokenView as TokenAuthView

@Composable
fun AppViewController(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Routes.LOGIN) {
            LoginView(navController)
        }
        composable(Routes.EMAIL_FORGOT_PASSWORD_FLOW) {
            EmailView(navController)
        }
        composable(Routes.EMAIL_SIGN_UP_FLOW) {
            EmailAuthView(navController)
        }
        composable(Routes.TOKEN_SIGN_UP_FLOW) {
            TokenAuthView(navController)
        }
        composable(Routes.SIGN_UP) {
            SingUpView(navController)
        }
        composable(Routes.TOKEN_FORGOT_PASSWORD_FLOW) {
            TokenView(navController)
        }
        composable(Routes.FORGOT_PASSWORD) {
            PasswordRecoveryView(navController)
        }
    }
}

@Preview
@Composable
fun previewuwu(){
    TaskerTheme {
        AppViewController()
    }
}