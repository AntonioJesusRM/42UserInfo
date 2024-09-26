package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
class ErrorModel(
    var error: String = "unknown",
    var code: Int = 401,
    var errorDescription: String = "unknown"
) : BaseModel()