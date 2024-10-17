package com.example.tasker.ui.viewmodel

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences by lazy {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            getApplication(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveAccessToken(token: String) {
        with(sharedPreferences.edit()) {
            putString("access_token", token)
            apply()
        }
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }

    fun clearTokens() {
        with(sharedPreferences.edit()) {
            remove("access_token")
            apply()
        }
    }
}
