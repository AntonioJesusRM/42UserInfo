package com.example.a42userinfo.domain.model

import kotlinx.parcelize.Parcelize

@Parcelize
class ErrorModel(
    var error: String = "unknown",
    var errorCode: String = "",
    var message: String = "unknown"
) : BaseModel()