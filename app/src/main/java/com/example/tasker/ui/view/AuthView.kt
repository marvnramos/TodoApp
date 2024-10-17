package com.example.tasker.ui.view

import com.example.tasker.ui.auth.view.SingUpView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tasker.ui.auth.view.LoginView
import com.example.tasker.ui.forgotpassword.view.EmailView
import com.example.tasker.ui.auth.view.EmailView as EmailAuthView
import com.example.tasker.ui.forgotpassword.view.PasswordRecoveryView
import com.example.tasker.ui.forgotpassword.view.TokenView
import com.example.tasker.ui.auth.view.TokenView as TokenAuthView
import com.example.tasker.ui.theme.TaskerTheme

object Routes {
    const val LOGIN = "Login"
    const val EMAIL_FORGOT_PASSWORD_FLOW = "EmailForgotPasswordFlow"
    const val EMAIL_SIGN_UP_FLOW = "EmailSignUpFlow"
    const val TOKEN_SIGN_UP_FLOW = "TokenSignUpFlow"
    const val SIGN_UP = "SingUp"
    const val TOKEN_FORGOT_PASSWORD_FLOW = "TokenForgotPasswordFlow"
    const val FORGOT_PASSWORD = "ForgotPassword"
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
fun AuthViewPreview() {
    TaskerTheme {
        AuthView()
    }
}
