package com.example.tasker.ui.view.screens

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.tasker.ui.view.components.auth.PasswordTextField
import com.example.tasker.ui.view.components.auth.UsernameTextField
import com.example.tasker.ui.view_model.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

@Composable
fun SingUpScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = AuthViewModel()
) {
    val context = LocalContext.current

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

    SingUpTemplate(
        onArrowClick = { navController.popBackStack() },
        onTextClick = { navController.navigate("Login") },
        onSubmitClick = { Toast.makeText(context, "Submit Clicked", Toast.LENGTH_SHORT).show() },
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

            Button(onClick = {
                val response = uploadImageToFirebase(imageUri!!)
                println("AAAAAAAAAAAAAAAAAA")
                println(response)
            }) {
                Text(text = "firebase test")
            }
        },
        addPhotoButtonEnable = true,
        onClickPhoto = {
            launcher.launch("image/*")
            println("Image URI: $imageUri")
        },
        imageUri = imageUri.toString()
    )
}

private fun uploadImageToFirebase(uri: Uri): String? {
    val user = FirebaseAuth.getInstance().currentUser
    var downloadUrl: String? = null
    if (user == null) {
        FirebaseAuth.getInstance().signInAnonymously()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = FirebaseAuth.getInstance().currentUser
                    uploadImageToFirebase(uri)
                } else {
                    Log.e("FirebaseAuth", "No se pudo autenticar anónimamente", task.exception)
                }
            }

    } else {
        downloadUrl = performUpload(uri)
    }
    return downloadUrl
}

private fun performUpload(uri: Uri): String? {
    val storageRef = Firebase.storage.reference
    val imagesRef = storageRef.child("profiles/${System.currentTimeMillis()}.jpg")

    var downloadUrl: String? = null

    val uploadTask = imagesRef.putFile(uri)
    uploadTask.addOnSuccessListener {
        imagesRef.downloadUrl.addOnSuccessListener { uri ->
            downloadUrl = uri.toString()
            Log.d("Firebase", "Download URL: $downloadUrl")
        }.addOnFailureListener { exception ->
            Log.e("Firebase", "Failed to get download URL", exception)
        }
    }.addOnFailureListener { exception ->
        Log.e("Firebase", "Upload failed", exception)
    }

    return downloadUrl
}


//@Preview
//@Composable
//fun SingUpScreenPreview() {
//    TaskerTheme {
//        SingUpScreen()
//    }
//}