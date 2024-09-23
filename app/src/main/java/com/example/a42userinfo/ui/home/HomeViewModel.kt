package com.example.a42userinfo.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a42userinfo.BuildConfig
import com.example.a42userinfo.data.constants.GeneralConstants.Companion.CLIENT_ID
import com.example.a42userinfo.data.constants.GeneralConstants.Companion.GRANT_TYPE
import com.example.a42userinfo.data.constants.GeneralConstants.Companion.REDIRECT_URI
import com.example.a42userinfo.data.repository.preferences.PreferencesDataSource
import com.example.a42userinfo.data.repository.remote.request.PostTokenRequest
import com.example.a42userinfo.data.repository.remote.response.BaseResponse
import com.example.a42userinfo.domain.usecase.GetTokenUseCase
import com.example.a42userinfo.ui.extensions.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class UiState {
    IDLE, LOADING, SUCCESS, ERROR
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val preferencesDataSource: PreferencesDataSource
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
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
                        is BaseResponse.Error -> {
                            _uiState.value = UiState.ERROR
                        }

                        is BaseResponse.Success -> {
                            Log.d(TAG, "%> Token: ${preferencesDataSource.getAuthToken()}")
                            Log.d(
                                TAG,
                                "%> Token refresh: ${preferencesDataSource.getRefreshToken()}"
                            )
                            _uiState.value = UiState.SUCCESS
                        }
                    }
                }
            }
        }
    }
}