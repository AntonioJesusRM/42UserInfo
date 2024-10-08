package com.example.a42userinfo.data.repository.preferences

import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.a42userinfo.ui.extensions.TAG
import javax.inject.Inject

class EncryptedSharedPreferencesManager @Inject constructor(
    private val encryptedSharedPreferences: EncryptedSharedPreferences
) {
    fun <T : Any?> set(key: String, value: T) {
        setValue(key, value)
    }

    private fun setValue(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value.toInt()) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value.toFloat()) }
            is Long -> edit { it.putLong(key, value.toLong()) }
            else -> {
                Log.e(TAG, "%> SharedPreferenceExtensions Unsupported Type: $value")
            }
        }
    }

    private fun edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.encryptedSharedPreferences.edit()
        operation(editor)
        editor.apply()
    }

    fun saveStringEncryptedSharedPreferences(key: String, value: String) {
        val start = System.currentTimeMillis()
        set(key, value)
        Log.d(
            TAG,
            "%> time - saveStringToDataStore key: $key, ${System.currentTimeMillis() - start}ms"
        )
    }

    fun getStringEncryptedSharedPreferences(key: String, defaultValue: String = ""): String {
        val start = System.currentTimeMillis()
        val test = encryptedSharedPreferences.getString(key, defaultValue) ?: ""
        Log.d(
            TAG,
            "%> time - getStringFromDataStore key: $key, ${System.currentTimeMillis() - start}ms"
        )
        return test
    }

    fun removeKeyFromEncryptedSharedPreferences(key: String) {
        val editor = this.encryptedSharedPreferences.edit()
        editor.remove(key).apply()
    }
}