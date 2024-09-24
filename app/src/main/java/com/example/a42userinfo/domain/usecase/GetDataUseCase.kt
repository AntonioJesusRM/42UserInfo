package com.example.a42userinfo.domain.usecase

import com.example.a42userinfo.data.repository.DataProvider
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.domain.model.GetDataModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val dataProvider: DataProvider) {
    operator fun invoke(): Flow<BaseResponse<GetDataModel>> {
        return dataProvider.getData()
    }
}