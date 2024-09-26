package com.example.a42userinfo.hilt

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.a42userinfo.data.repository.preferences.PreferencesDataSource
import com.example.a42userinfo.data.session.DataUserSession
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SimpleApplication : Application() {

    @Inject
    lateinit var preferencesDataSource: PreferencesDataSource

    @Inject
    lateinit var dataUserSession: DataUserSession

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initSession()
    }

    private fun initSession() {
        if (preferencesDataSource.getAuthToken().isNotBlank()) {
            dataUserSession.tokenRefresh = preferencesDataSource.getRefreshToken()
        }
    }
}