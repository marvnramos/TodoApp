package com.example.tasker.ui.view.auth.viewmodel

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
    private val _password = MutableLiveData<String>()
    private val _confirmedPassword = MutableLiveData<String>()
    private val _username = MutableLiveData<String>()
    private val _token = MutableLiveData<String>()

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val confirmedPassword: LiveData<String> = _confirmedPassword
    val username: LiveData<String> = _username
    val token: LiveData<String> = _token

    private fun String.isValidNumber(): Boolean {
        val numberRegex = Regex("^\\d*\$")
        return this.matches(numberRegex)
    }

    fun comparePasswords(
        password: MutableLiveData<String>,
        confirmedPassword: MutableLiveData<String>
    ): Boolean {
        return password.value == confirmedPassword.value
    }

    fun isTokenValid(token: String): Boolean {
        if (token.isNotEmpty()) {
            return token.length == 6 && token.isValidNumber()
        }
        return false
    }

    fun isEmailValid(email: MutableLiveData<String>): Boolean {
        val emailValue = email.value.toString()
        return Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()
    }

    fun onValueChanged(
        email: String? = null,
        password: String? = null,
        username: String? = null,
        confirmedPassword: String? = null,
        token: String? = null,
    ) {
        email?.let { _email.value = it }
        password?.let { _password.value = it }
        confirmedPassword?.let { _confirmedPassword.value = it }
        token?.let { _token.value = it }
        username?.let { _username.value = it }
    }

    fun isPasswordValid(password: MutableLiveData<String>): List<String> {
        val errorMessagesList = mutableListOf<String>()

        if (!_atLeastOneDigit.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Debe contener al menos un digito.")
        }
        if (!_atLeastOneLowerCase.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Debe contener al menos una minúscula.")
        }
        if (!_atLeastOneUpperCase.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Debe contener al menos una mayúscula.")
        }
        if (!_atLeastOneSpecialChar.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Debe contener al menos un caracter especial.")
        }
        if (!_minimumLength.containsMatchIn(password.value.toString())) {
            errorMessagesList.add("Debe contener al menos 8 caracteres.")
        }

        return errorMessagesList
    }
}