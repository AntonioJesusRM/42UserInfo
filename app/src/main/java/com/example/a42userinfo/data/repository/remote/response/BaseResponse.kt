package com.example.a42userinfo.data.repository.remote.response

import com.example.a42userinfo.domain.model.ErrorModel

sealed class BaseResponse<T> {
    class Success<T>(val data: T) : BaseResponse<T>()
    class Error<T>(val error: ErrorModel) : BaseResponse<T>()
}