package com.example.a42userinfo.data.repository.remote.backend

import com.example.a42userinfo.data.mapper.GetCoalitionMapper
import com.example.a42userinfo.data.mapper.GetDataMapper
import com.example.a42userinfo.data.repository.preferences.PreferencesDataSource
import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.domain.model.CoalitionModel
import com.example.a42userinfo.domain.model.ErrorModel
import com.example.a42userinfo.domain.model.GetDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val callApiService: CallApiService,
    private val preferencesDataSource: PreferencesDataSource
) : BaseService() {

    //Get Token
    fun getToken(postTokenRequest: PostTokenRequest): Flow<BaseResponse<Boolean>> = flow {
        val apiResult = callApiService.callPostToken(postTokenRequest)
        if (apiResult is BaseResponse.Success) {
            apiResult.data.let {
                preferencesDataSource.apply {
                    saveAuthToken(apiResult.data.token ?: "")
                    saveRefreshToken(apiResult.data.refreshToken ?: "")
                }
            }
            if (!apiResult.data.token.isNullOrEmpty() && !apiResult.data.refreshToken.isNullOrEmpty())
                emit(BaseResponse.Success(true))
            else
                emit(BaseResponse.Error(ErrorModel("Empty data", "", "")))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }

    //Get Data
    fun getData(): Flow<BaseResponse<GetDataModel>> = flow {
        val apiResult = callApiService.callGetData()
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(GetDataMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }

    //Get Coalition
    fun getCoalition(id: Int): Flow<BaseResponse<CoalitionModel>> = flow {
        val apiResult = callApiService.callGetCoalition(id)
        if (apiResult is BaseResponse.Success) {
            emit(BaseResponse.Success(GetCoalitionMapper().fromResponse(apiResult.data)))
        } else if (apiResult is BaseResponse.Error) {
            emit(BaseResponse.Error(apiResult.error))
        }
    }
}