package com.example.a42userinfo.data.repository

import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow

interface DataSource {
    //GetToken
    fun getToken(postTokenRequest: PostTokenRequest): Flow<BaseResponse<Boolean>>
}