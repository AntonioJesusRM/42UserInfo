package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class GetDataModel(
    val userId: Int,
    val userImg: String,
    val login: String,
    val email: String,
    val correctionPoint: String,
    val cursusUsers: GetUserSkillModel,
    val projectsUsers: List<GetUserProjectModel>
) : BaseModel()
