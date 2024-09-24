package com.example.a42userinfo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a42userinfo.BuildConfig
import com.example.a42userinfo.data.constants.GeneralConstants.Companion.CLIENT_ID
import com.example.a42userinfo.data.constants.GeneralConstants.Companion.GRANT_TYPE
import com.example.a42userinfo.data.constants.GeneralConstants.Companion.REDIRECT_URI
import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.domain.usecase.GetCoalitionUseCase
import com.example.a42userinfo.domain.usecase.GetDataUseCase
import com.example.a42userinfo.domain.usecase.GetTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class UiState {
    LOADING, SUCCESS, ERROR
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val getDataUseCase: GetDataUseCase,
    private val getCoalitionUseCase: GetCoalitionUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.LOADING)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun getToken(code: String?) {
        if (code != null) {
            val tokenRequest = PostTokenRequest(
                grantType = GRANT_TYPE,
                clientId = CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET,
                code = code,
                redirectUri = REDIRECT_URI
            )
            viewModelScope.launch {
                _uiState.value = UiState.LOADING
                getTokenUseCase(tokenRequest).collect {
                    when (it) {
                        is BaseResponse.Error -> _uiState.value = UiState.ERROR

                        is BaseResponse.Success -> getData()
                    }
                }
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            getDataUseCase().collect {
                when (it) {
                    is BaseResponse.Error -> _uiState.value = UiState.ERROR

                    is BaseResponse.Success -> getCoalition(it.data.userId)
                }
            }
        }
    }

    private fun getCoalition(id: Int) {
        viewModelScope.launch {
            getCoalitionUseCase(id).collect {
                when (it) {
                    is BaseResponse.Error -> _uiState.value = UiState.ERROR

                    is BaseResponse.Success -> _uiState.value = UiState.SUCCESS
                }
            }
        }
    }
}