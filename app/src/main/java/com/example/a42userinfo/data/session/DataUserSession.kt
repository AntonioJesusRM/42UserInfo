package com.example.a42userinfo.data.session

import android.util.Log
import com.example.a42userinfo.ui.extensions.TAG
import java.io.Serializable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataUserSession @Inject constructor() : Serializable {
    var tokenRefresh: String = ""

    fun haveSession(): Boolean {
        Log.d(TAG, "%> El valor del refresh token: $tokenRefresh")
        return tokenRefresh.isNotEmpty()
    }
}