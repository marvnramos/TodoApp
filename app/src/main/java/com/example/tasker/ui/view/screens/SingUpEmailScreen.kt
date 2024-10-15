package com.example.tasker.ui.view.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.tasker.ui.view.components.auth.EmailTextField
import com.example.tasker.ui.view_model.AuthViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpEmailScreen(
    navController: NavHostController,
    authVM: AuthViewModel = AuthViewModel()
) {
    val email by authVM.email.observeAsState(initial = "")
    val isValidEmail =
        if (email.isNotEmpty()) authVM.isEmailValid(MutableLiveData(email)) else true

    val errorMessage = if (!isValidEmail) "Dirección de correo inválida" else null

    val isError = errorMessage != null
    val isAvailable = email.isNotEmpty() && !isError
    val context = LocalContext.current
    SingUpTemplate(
        onArrowClick = { navController.popBackStack() },
        onTextClick = { navController.popBackStack() },
        onSubmitClick = { Toast.makeText(context, "Submit Clicked", Toast.LENGTH_SHORT).show() },
        subWelcomeText = "Registra una nueva cuenta",
        textIndicator = null,
        submitText = "Siguiente",
        isAvailable = isAvailable,
        textFieldComponent = {
            EmailTextField(
                text = email,
                isValid = isValidEmail,
                errorMessage = errorMessage,
                onTextChanged = { authVM.onValueChanged(email = it) }
            )

        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview() {
//    TaskerTheme {
//        SignUpEmailScreen()
//    }
//}
