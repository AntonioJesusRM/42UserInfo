package com.example.a42userinfo.data.constants

class GeneralConstants {
    companion object {
        //Retrofit
        const val RETROFIT_TIMEOUT_IN_SECOND: Long = 30
        const val BASE_URL: String = "https://api.intra.42.fr/"
        const val REDIRECT_URI: String = "com.example.a42userinfo://oauth/callback"
        const val GRANT_TYPE: String = "authorization_code"
    }
}