package com.example.a42userinfo.data.mapper

import com.example.a42userinfo.data.repository.remote.response.GetDataResponse
import com.example.a42userinfo.domain.model.GetDataModel

class GetDataMapper : ResponseMapper<GetDataResponse, GetDataModel> {
    override fun fromResponse(response: GetDataResponse): GetDataModel {
        val projects = response.projectsUsers?.filter {
            it.cursusIds?.contains(21) == true
        }?.map { GetUserProjectMapper().fromResponse(it) } ?: emptyList()
        val cursus42 = response.cursusUsers?.find { it.cursusId == 21 }
        return GetDataModel(
            login = response.login ?: "",
            userImg = response.image.versions?.small ?: "",
            email = response.email ?: "",
            correctionPoint = response.correctionPoint.toString(),
            cursusUsers = GetCursusMapper().fromResponse(cursus42),
            projectsUsers = projects
        )
    }
}