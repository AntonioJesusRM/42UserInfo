package com.example.a42userinfo.data.repository.preferences

import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val encryptedSharedPreferencesManager: EncryptedSharedPreferencesManager
) {
    fun saveAuthToken(token: String) {
        encryptedSharedPreferencesManager.saveStringEncryptedSharedPreferences(
            EncryptedSharedPreferencesKeys.ENCRYPTED_SHARED_PREFERENCES_KEY_LOGIN_AUTH,
            token
        )
    }

    fun getAuthToken(): String {
        return encryptedSharedPreferencesManager.getStringEncryptedSharedPreferences(
            EncryptedSharedPreferencesKeys.ENCRYPTED_SHARED_PREFERENCES_KEY_LOGIN_AUTH
        )
    }

    fun saveRefreshToken(token: String) {
        encryptedSharedPreferencesManager.saveStringEncryptedSharedPreferences(
            EncryptedSharedPreferencesKeys.ENCRYPTED_SHARED_PREFERENCES_KEY_REFRESH_TOKEN,
            token
        )
    }

    fun getRefreshToken(): String {
        return encryptedSharedPreferencesManager.getStringEncryptedSharedPreferences(
            EncryptedSharedPreferencesKeys.ENCRYPTED_SHARED_PREFERENCES_KEY_REFRESH_TOKEN
        )
    }
}