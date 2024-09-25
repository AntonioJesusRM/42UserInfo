package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
data class CoalitionModel(
    val nameCoalition: String,
    val imgCoalition: String
) : BaseModel()
