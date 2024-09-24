package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class SkillModel(
    val name: String,
    val level: String
) : BaseModel()