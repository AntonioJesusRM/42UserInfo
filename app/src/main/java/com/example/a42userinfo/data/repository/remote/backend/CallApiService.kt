package com.example.a42userinfo.data.repository.remote.backend

import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.data.repository.remote.response.GetDataResponse
import com.example.a42userinfo.data.repository.remote.response.PostTokenResponse
import javax.inject.Inject

class CallApiService @Inject constructor(
    private val apiService: ApiService
) : BaseService() {
    suspend fun callPostToken(postTokenRequest: PostTokenRequest): BaseResponse<PostTokenResponse> {
        return apiCall {
            apiService.postToken(
                postTokenRequest.clientId,
                postTokenRequest.clientSecret,
                postTokenRequest.code,
                postTokenRequest.redirectUri,
                postTokenRequest.grantType
            )
        }
    }

    suspend fun callGetData(): BaseResponse<GetDataResponse> {
        return apiCall { apiService.getData() }
    }
}