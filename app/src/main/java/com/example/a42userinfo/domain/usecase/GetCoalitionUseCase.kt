package com.example.a42userinfo.domain.usecase

import com.example.a42userinfo.data.repository.DataProvider
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.domain.model.CoalitionModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoalitionUseCase @Inject constructor(private val dataProvider: DataProvider) {
    operator fun invoke(id: Int): Flow<BaseResponse<CoalitionModel>> {
        return dataProvider.getCoalition(id)
    }
}