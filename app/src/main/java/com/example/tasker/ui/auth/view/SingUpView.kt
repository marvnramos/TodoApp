package com.example.tasker.ui.auth.view

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.tasker.ui.auth.components.PasswordTextField
import com.example.tasker.ui.auth.components.UsernameTextField
import com.example.tasker.ui.auth.viewmodel.AuthViewModel
import com.example.tasker.ui.view.commons.TemplateView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun SingUpView(
    navController: NavHostController,
    viewModel: AuthViewModel = AuthViewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val password by viewModel.password.observeAsState(initial = "")
    var comparePasswordErrorMessages by remember { mutableStateOf<List<String>>(emptyList()) }
    val username by viewModel.username.observeAsState(initial = "")
    var errorMessages by remember { mutableStateOf<List<String>>(emptyList()) }
    val confirmedPassword by viewModel.confirmedPassword.observeAsState(initial = "")

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val isValid = if (password.isNotEmpty()) {
        errorMessages = viewModel.isPasswordValid(MutableLiveData(password))
        errorMessages.isEmpty()
    } else {
        errorMessages = emptyList()
        true
    }

    val samePassword = if (confirmedPassword.isNotEmpty()) {
        if (!viewModel.comparePasswords(
                MutableLiveData(password),
                MutableLiveData(confirmedPassword)
            )
        ) {
            comparePasswordErrorMessages = listOf("Las contraseñas no coinciden.")
            false
        } else {
            comparePasswordErrorMessages = emptyList()
            true
        }
    } else {
        comparePasswordErrorMessages = emptyList()
        true
    }

    val isAvailable =
        username.isNotEmpty() && password.isNotEmpty() && confirmedPassword.isNotEmpty() && isValid && samePassword

    TemplateView(
        onArrowClick = { navController.popBackStack() },
        onTextClick = { navController.navigate("Login") },
        onSubmitClick = {
            imageUri?.let {
                scope.launch {
                    val downloadUrl = uploadImageToFirebase(it)
                    Log.d("Firebase", "Download URL: $downloadUrl")
                }
            } ?: Toast.makeText(context, "No image selected", Toast.LENGTH_SHORT).show()
        },
        subWelcomeText = "Ingresa tus datos",
        textIndicator = null,
        submitText = "Registrarse",
        isAvailable = isAvailable,
        textFieldComponent = {
            UsernameTextField(
                text = username,
                onValueChange = { viewModel.onValueChanged(username = it) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(
                text = password,
                availableValidation = true,
                isValid = isValid,
                errorMessages = errorMessages,
                onTextChanged = { viewModel.onValueChanged(password = it) }
            )

            PasswordTextField(
                label = "Confirmar contraseña",
                text = confirmedPassword,
                availableValidation = true,
                isValid = samePassword,
                errorMessages = comparePasswordErrorMessages,
                onTextChanged = { viewModel.onValueChanged(confirmedPassword = it) }
            )
        },
        addPhotoButtonEnable = true,
        onClickPhoto = { launcher.launch("image/*") },
        imageUri = imageUri?.toString() ?: ""
    )
}

private suspend fun uploadImageToFirebase(uri: Uri): String {
    FirebaseAuth.getInstance().currentUser
        ?: FirebaseAuth.getInstance().signInAnonymously().await().user

    return performUpload(uri)
}

private suspend fun performUpload(uri: Uri): String {
    val storageRef = Firebase.storage.reference
    val imagesRef = storageRef.child("profiles/${System.currentTimeMillis()}.jpg")

    return try {
        imagesRef.putFile(uri).await()
        val downloadUrl = imagesRef.downloadUrl.await()
        downloadUrl.toString()
    } catch (e: Exception) {
        Log.e("Firebase", "Upload failed", e)
        "No se pudo subir la imagen"
    }
}
