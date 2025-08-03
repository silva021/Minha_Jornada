package com.silva021.minhajornada.ui.screens.communities.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.domain.usecases.GetCommunityByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommunityDetailsViewModel(
    private val getCommunityById: GetCommunityByIdUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CommunityDetailsUiState>(CommunityDetailsUiState.Loading)
    val uiState: StateFlow<CommunityDetailsUiState> = _uiState.asStateFlow()

    fun fetchCommunityDetails(communityId: String) {
        viewModelScope.launch {
            if (_uiState.value.isSuccess().not()) {
                _uiState.value = CommunityDetailsUiState.Loading
                getCommunityById(communityId).onSuccess {
                    _uiState.value = CommunityDetailsUiState.Success(it)
                }.onFailure {
                    _uiState.value = CommunityDetailsUiState.Error(
                        it.message ?: "Erro ao carregar detalhes da comunidade"
                    )
                }
            }
        }
    }

}

sealed class CommunityDetailsUiState {
    data class Success(val community: Community) : CommunityDetailsUiState()
    object Loading : CommunityDetailsUiState()
    data class Error(val message: String) : CommunityDetailsUiState()

    fun isSuccess() = this is Success
}