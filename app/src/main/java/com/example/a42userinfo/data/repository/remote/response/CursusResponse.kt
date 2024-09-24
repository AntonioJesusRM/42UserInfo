package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class CursusResponse(
    @SerializedName("grade") val grade: String?,
    @SerializedName("level") val level: String?,
    @SerializedName("skills") val skills: List<GetSkillsResponse>?,
    @SerializedName("blackholed_at") val blackholedAt: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("begin_at") val beginAt: String?,
    @SerializedName("end_at") val endAt: String?,
    @SerializedName("cursus_id") val cursusId: Int?,
    @SerializedName("has_coalition") val hasCoalition: Boolean?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)