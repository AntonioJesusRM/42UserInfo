package com.example.a42userinfo.data.repository

import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.domain.model.CoalitionModel
import com.example.a42userinfo.domain.model.GetDataModel
import kotlinx.coroutines.flow.Flow

interface DataSource {
    //GetToken
    fun getToken(postTokenRequest: PostTokenRequest): Flow<BaseResponse<Boolean>>

    //GetData
    fun getData(): Flow<BaseResponse<GetDataModel>>

    //GetCoalition
    fun getCoalition(id: Int): Flow<BaseResponse<CoalitionModel>>
}