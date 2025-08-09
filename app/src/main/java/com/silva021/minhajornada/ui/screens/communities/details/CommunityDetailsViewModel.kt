package com.silva021.minhajornada.ui.screens.communities.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.domain.usecases.GetCommunityByIdUseCase
import com.silva021.minhajornada.domain.usecases.JoinCommunityUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommunityDetailsViewModel(
    private val getCommunityById: GetCommunityByIdUseCase,
    private val joinCommunityUseCase: JoinCommunityUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CommunityDetailsUiState>(CommunityDetailsUiState.Loading)
    val uiState: StateFlow<CommunityDetailsUiState> = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()

    fun joinCommunity(communityId: String) {
        viewModelScope.launch {
            _uiState.value = CommunityDetailsUiState.Loading
            joinCommunityUseCase.invoke(communityId).onSuccess {
                _eventFlow.emit(NavigationEvent.NavigateToCommunityFeed)
            }.onFailure {
                _uiState.value = CommunityDetailsUiState.Error(it.message ?: "Erro ao entrar na comunidade")
            }
        }
    }

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

sealed class NavigationEvent {
    data object NavigateToCommunityFeed : NavigationEvent()
}