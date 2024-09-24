package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class GetUserDataModel(
    val userImg: String,
    val name: String,
    val email: String,
    val level: String,
    val evPoints: String
) : BaseModel()