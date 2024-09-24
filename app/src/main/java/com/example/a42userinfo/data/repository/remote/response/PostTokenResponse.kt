package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class PostTokenResponse(
    @SerializedName("access_token")
    val token: String?,
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("expires_in")
    val expiresIn: String?,
    @SerializedName("refresh_token")
    val refreshToken: String?,
    @SerializedName("scope")
    val scope: String?,
    @SerializedName("created_at")
    val created: String?,
    @SerializedName("secret_valid_until")
    val secretValid: String?
)