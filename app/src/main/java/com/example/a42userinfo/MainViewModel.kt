package com.example.a42userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class UiState {
    IDLE, LOADING, SUCCESS, ERROR
}

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState.IDLE)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun requestToken(authCode: String) {
        viewModelScope.launch {
            _uiState.value = UiState.LOADING
        }
    }
}