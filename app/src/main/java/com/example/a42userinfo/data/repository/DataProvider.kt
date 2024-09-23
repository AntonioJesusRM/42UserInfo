package com.example.a42userinfo.data.repository

import com.example.a42userinfo.data.repository.remote.backend.RemoteDataSource
import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataProvider @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : DataSource {

    //Get Token
    override fun getToken(postTokenRequest: PostTokenRequest): Flow<BaseResponse<Boolean>> {
        return remoteDataSource.getToken(postTokenRequest)
    }

}