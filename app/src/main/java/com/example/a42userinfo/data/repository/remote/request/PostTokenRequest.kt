package com.example.a42userinfo.data.repository.remote.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostTokenRequest(
    @SerializedName("grant_type") var grantType: String,
    @SerializedName("client_id") var clientId: String,
    @SerializedName("client_secret") var clientSecret: String,
    @SerializedName("code") var code: String,
    @SerializedName("redirect_uri") var redirectUri: String,
) : Serializable