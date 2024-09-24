package com.example.a42userinfo.data.mapper

import com.example.a42userinfo.data.repository.remote.response.ProjectResponse
import com.example.a42userinfo.domain.model.GetUserProjectModel

class GetUserProjectMapper : ResponseMapper<ProjectResponse, GetUserProjectModel> {
    override fun fromResponse(response: ProjectResponse): GetUserProjectModel {
        return (GetUserProjectModel(
            name = response.project?.name ?: "",
            status = response.status ?: "",
            finalMark = response.finalMark.toString()
        ))
    }
}
