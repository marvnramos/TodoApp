package com.example.tasker.ui.view_model

import android.support.v4.os.IResultReceiver._Parcel
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    private val _atLeastOneLowerCase = Regex(".*[a-z].*")
    private val _atLeastOneUpperCase = Regex(".*[A-Z].*")
    private val _atLeastOneDigit = Regex(".*[0-9].*")
    private val _atLeastOneSpecialChar = Regex(".*[!@#\$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?].*")
    private val _minimumLength = Regex(".{8,}")

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmedPassword = MutableLiveData<String>()
    val confirmedPassword: LiveData<String> = _confirmedPassword

    fun comparePasswords(
        password: MutableLiveData<String>,
        confirmedPassword: MutableLiveData<String>
    ): Boolean {
        return password.value == confirmedPassword.value
    }

    fun isEmailValid(email: MutableLiveData<String>): Boolean {
        val emailValue = email.value.toString()
        return Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()
    }

    fun onValueChanged(
        email: String? = null,
        password: String? = null,
        confirmedPassword: String? = null
    ) {
        email?.let { _email.value = it }
        password?.let { _password.value = it }
        confirmedPassword?.let { _confirmedPassword.value = it }
    }

    fun isPasswordValid(password: MutableLiveData<String>): List<String> {
        val errorMessagesList = mutableListOf<String>()

        if (!_atLeastOneDigit.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Password must contain at least one digit.")
        }
        if (!_atLeastOneLowerCase.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Password must contain at least one lowercase letter.")
        }
        if (!_atLeastOneUpperCase.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Password must contain at least one uppercase letter.")
        }
        if (!_atLeastOneSpecialChar.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Password must contain at least one special character.")
        }
        if (!_minimumLength.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Password must contain at least 8 characters.")
        }

        return errorMessagesList
    }
}