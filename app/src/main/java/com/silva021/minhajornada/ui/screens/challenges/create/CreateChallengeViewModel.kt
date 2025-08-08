package com.silva021.minhajornada.ui.screens.challenges.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.usecases.CreateChallengeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateChallengeViewModel(
    private val createChallenge: CreateChallengeUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CreateChallengeUiState>(CreateChallengeUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun createChallenge(challenge: Challenge) {
        viewModelScope.launch {
            _uiState.value = CreateChallengeUiState.Loading
            createChallenge.invoke(challenge)
                .onSuccess {
                    _uiState.value = CreateChallengeUiState.Success
                }.onFailure {
                    _uiState.value = CreateChallengeUiState.Error(
                        message = it.message ?: "An error occurred while fetching challenges"
                    )
                }
        }
    }
}

sealed class CreateChallengeUiState {
    object Success : CreateChallengeUiState()
    object Loading : CreateChallengeUiState()
    object Idle : CreateChallengeUiState()
    data class Error(val message: String) : CreateChallengeUiState()
}