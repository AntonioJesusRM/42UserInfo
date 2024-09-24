package com.example.a42userinfo.data.repository

import com.example.a42userinfo.data.repository.remote.backend.RemoteDataSource
import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.domain.model.CoalitionModel
import com.example.a42userinfo.domain.model.GetDataModel
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

    //Get Data
    override fun getData(): Flow<BaseResponse<GetDataModel>> {
        return remoteDataSource.getData()
    }

    //Get Coalition
    override fun getCoalition(id: Int): Flow<BaseResponse<CoalitionModel>> {
        return remoteDataSource.getCoalition(id)
    }

}