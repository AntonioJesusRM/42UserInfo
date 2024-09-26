package com.example.a42userinfo.data.mapper

import com.example.a42userinfo.data.repository.remote.response.CursusResponse
import com.example.a42userinfo.domain.model.GetUserSkillModel

class GetCursusMapper : ResponseMapper<CursusResponse?, GetUserSkillModel> {
    override fun fromResponse(response: CursusResponse?): GetUserSkillModel {
        val skills = response?.skills?.map { GetSkillMapper().fromResponse(it) } ?: emptyList()

        return GetUserSkillModel(
            levelCursus = response?.level.toString(),
            skills = skills
        )
    }
}