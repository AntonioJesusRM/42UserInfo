package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error") var error: String?,
    var errorCode: Int?,
    @SerializedName("error_description") var message: String?
)