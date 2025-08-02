package com.silva021.minhajornada.ui.screens.communities.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Communities
import com.silva021.minhajornada.domain.usecases.GetCommunitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommunitiesViewModel(
    private val getCommunities: GetCommunitiesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CommunitiesUiState>(CommunitiesUiState.Loading)
    val uiState: StateFlow<CommunitiesUiState> = _uiState.asStateFlow()

    fun fetchCommunities() {
        if (_uiState.value.isSuccess().not())
            viewModelScope.launch {
                _uiState.value = CommunitiesUiState.Loading
                getCommunities().onSuccess {
                    _uiState.value = CommunitiesUiState.Success(it)
                }.onFailure {
                    _uiState.value = CommunitiesUiState.Error(
                        it.message ?: "Erro ao carregar comunidades"
                    )
                }
            }
    }
}

sealed class CommunitiesUiState {
    data class Success(val communities: Communities) : CommunitiesUiState()
    object Loading : CommunitiesUiState()
    data class Error(val message: String) : CommunitiesUiState()

    fun isSuccess() = this is Success
}