package com.example.tasker.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.view.screens.AuthScreen
import com.example.tasker.ui.view.screens.EmailScreen
import com.example.tasker.ui.view.screens.SignUpEmailScreen
import com.example.tasker.ui.view.screens.SingUpScreen
import com.example.tasker.ui.view.screens.SingUpTokenScreen
import com.example.tasker.ui.view.theme.TaskerTheme

object Routes {
    const val LOGIN = "Login"
    const val EMAIL_FORGOT_PASSWORD_FLOW = "EmailForgotPasswordFlow"
    const val EMAIL_SIGN_UP_FLOW = "EmailSignUpFlow"
    const val TOKEN_SIGN_UP_FLOW = "TokenSignUpFlow"
    const val SIGN_UP = "SingUp"
}

@Composable
fun AuthView() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Routes.LOGIN) {
            AuthScreen(navController)
        }
        composable(Routes.EMAIL_FORGOT_PASSWORD_FLOW) {
            EmailScreen(navController)
        }
        composable(Routes.EMAIL_SIGN_UP_FLOW) {
            SignUpEmailScreen(navController)
        }
        composable(Routes.TOKEN_SIGN_UP_FLOW) {
            SingUpTokenScreen(navController)
        }
        composable(Routes.SIGN_UP) {
            SingUpScreen(navController)
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
