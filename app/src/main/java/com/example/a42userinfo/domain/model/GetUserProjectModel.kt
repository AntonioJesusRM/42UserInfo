package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class GetUserProjectModel(
    val name: String,
    val status: String,
    val finalMark: String
) : BaseModel()