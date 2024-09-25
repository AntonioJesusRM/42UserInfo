package com.example.a42userinfo.data.mapper

import com.example.a42userinfo.data.repository.remote.response.GetCoalitionResponse
import com.example.a42userinfo.domain.model.CoalitionModel

class GetCoalitionMapper : ResponseMapper<List<GetCoalitionResponse>, CoalitionModel> {
    override fun fromResponse(response: List<GetCoalitionResponse>): CoalitionModel {
        val firstCoalition = response.firstOrNull()
        return CoalitionModel(
            nameCoalition = firstCoalition?.name ?: "",
            imgCoalition = firstCoalition?.imageUrl ?: ""
        )
    }
}