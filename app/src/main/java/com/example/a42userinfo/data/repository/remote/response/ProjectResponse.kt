package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class ProjectResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("occurrence") val occurrence: Int?,
    @SerializedName("final_mark") val finalMark: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("validated?") val validated: Boolean?,
    @SerializedName("current_team_id") val currentTeamId: Int?,
    @SerializedName("project") val project: DataProjectResponse?,
    @SerializedName("cursus_ids") val cursusIds: List<Int>?,
    @SerializedName("marked_at") val markedAt: String?,
    @SerializedName("marked") val marked: Boolean?,
    @SerializedName("retriable_at") val retriableAt: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?
)
