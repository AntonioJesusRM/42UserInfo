package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class ImageVersionsResponse(
    @SerializedName("large")
    val large: String?,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("micro")
    val micro: String?
)