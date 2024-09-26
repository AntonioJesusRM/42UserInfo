package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class DataProjectResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("slug") val slug: String?,
    @SerializedName("parent_id") val parentId: Int?
)
