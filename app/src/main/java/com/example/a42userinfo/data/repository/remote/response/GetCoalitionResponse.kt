package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class GetCoalitionResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("slug") val slug: String?,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("cover_url") val coverUrl: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("score") val score: Int?,
    @SerializedName("user_id") val userId: Int?
)