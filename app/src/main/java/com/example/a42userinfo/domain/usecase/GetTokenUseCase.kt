package com.example.a42userinfo.domain.usecase

import com.example.a42userinfo.data.repository.DataProvider
import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(private val dataProvider: DataProvider) {
    operator fun invoke(postTokenRequest: PostTokenRequest): Flow<BaseResponse<Boolean>> {
        return dataProvider.getToken(postTokenRequest)
    }
}