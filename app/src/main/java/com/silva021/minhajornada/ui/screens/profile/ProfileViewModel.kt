package com.silva021.minhajornada.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.domain.usecases.GetMyProfileUseCase
import com.silva021.minhajornada.ui.screens.challenges.mine.ChallengesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfile: GetMyProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun fetchProfile() {
        if (_uiState.value.isSuccess().not()) {
            viewModelScope.launch {
                _uiState.value = ProfileUiState.Loading
                getProfile().onSuccess {
                    _uiState.value = ProfileUiState.Success(it)
                }.onFailure {
                    _uiState.value = ProfileUiState.Error(
                        message = it.message ?: "Erro ao carregar perfil"
                    )
                }
            }
        }
    }
}

sealed class ProfileUiState {
    data class Success(val profile: Profile) : ProfileUiState()
    object Loading : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()

    fun isSuccess() = this is Success
}