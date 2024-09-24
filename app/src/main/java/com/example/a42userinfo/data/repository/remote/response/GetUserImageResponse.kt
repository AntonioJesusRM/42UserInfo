package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class GetUserImageResponse(
    @SerializedName("link")
    val link: String?,
    @SerializedName("versions")
    val versions: ImageVersionsResponse?,
)