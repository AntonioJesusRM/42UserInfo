package com.example.a42userinfo.data.mapper

import com.example.a42userinfo.data.repository.remote.response.GetSkillsResponse
import com.example.a42userinfo.domain.model.SkillModel

class GetSkillMapper : ResponseMapper<GetSkillsResponse?, SkillModel> {
    override fun fromResponse(response: GetSkillsResponse?): SkillModel {
        return SkillModel(
            name = response?.name ?: "",
            level = response?.level?.toString() ?: ""
        )
    }
}