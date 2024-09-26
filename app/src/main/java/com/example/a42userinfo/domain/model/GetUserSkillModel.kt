package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class GetUserSkillModel(
    val levelCursus: String,
    val skills: List<SkillModel>
) : BaseModel()