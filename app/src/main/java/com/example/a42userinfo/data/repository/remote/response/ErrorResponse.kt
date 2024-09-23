package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error") var error: String?,
    @SerializedName("errorCode") var errorCode: String?,
    @SerializedName("message") var message: String?
)