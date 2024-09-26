package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class GetSkillsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("level")
    val level: Double?
)
