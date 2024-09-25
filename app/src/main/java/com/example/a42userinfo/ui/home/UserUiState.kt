package com.example.a42userinfo.ui.home

import com.example.a42userinfo.domain.model.GetUserProjectModel
import com.example.a42userinfo.domain.model.SkillModel

data class UserUiState(
    val login: String = "",
    val email: String = "",
    val level: String = "",
    val evPoints: String = "",
    val imgUser: String = "",
    val listProjects: List<GetUserProjectModel> = listOf(),
    val skills: List<SkillModel> = listOf(),
    val imgCoalition: String = "",
    val coalition: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
