package com.example.a42userinfo.data.repository.remote.backend

import com.example.a42userinfo.data.repository.preferences.PreferencesDataSource
import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.domain.model.ErrorModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val callApiService: CallApiService,
    private val preferencesDataSource: PreferencesDataSource
) : BaseService() {
    //Get Token
    fun getToken(postTokenRequest: PostTokenRequest): Flow<BaseResponse<Boolean>> = flow {
        val apiResult = callApiService.callGetToken(postTokenRequest)
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
}