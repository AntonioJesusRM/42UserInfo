package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class GetUserSkillModel(
    val name: String,
    val level: String,
    val percentage: String
) : BaseModel()