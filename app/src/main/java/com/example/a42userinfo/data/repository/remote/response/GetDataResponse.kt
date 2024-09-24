package com.example.a42userinfo.data.repository.remote.response

import com.google.gson.annotations.SerializedName

data class GetDataResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("login") val login: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("usual_full_name") val usualFullName: String?,
    @SerializedName("usual_first_name") val usualFirstName: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("displayname") val displayName: String?,
    @SerializedName("kind") val kind: String?,
    @SerializedName("image") val image: GetUserImageResponse,
    @SerializedName("staff?") val staff: Boolean?,
    @SerializedName("correction_point") val correctionPoint: Int?,
    @SerializedName("pool_month") val poolMonth: String?,
    @SerializedName("pool_year") val poolYear: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("wallet") val wallet: Int?,
    @SerializedName("anonymize_date") val anonymizeDate: String?,
    @SerializedName("data_erasure_date") val dataErasureDate: String?,
    @SerializedName("created_at?") val createdAt: String?,
    @SerializedName("updated_at") val updatedAt: String?,
    @SerializedName("alumnized_at") val alumnizedAt: String?,
    @SerializedName("alumni") val alumni: Boolean?,
    @SerializedName("active") val active: Boolean?,
    @SerializedName("cursus_users") val cursusUsers: List<CursusResponse>?,
    @SerializedName("projects_users") val projectsUsers: List<ProjectResponse>?
)
