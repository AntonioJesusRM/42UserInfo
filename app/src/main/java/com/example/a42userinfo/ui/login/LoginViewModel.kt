package com.example.a42userinfo.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a42userinfo.data.repository.preferences.PreferencesDataSource
import com.example.a42userinfo.ui.extensions.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : ViewModel() {
    fun logOut() {
        if (preferencesDataSource.getAuthToken()
                .isBlank() || preferencesDataSource.getRefreshToken().isBlank()
        ) {
            Log.d(TAG, "%> No existe el tokken")
        } else {
            Log.d(TAG, "%> Borrando token")
            preferencesDataSource.deleteAuthToken()
            preferencesDataSource.deleteRefreshToken()
        }
    }
}