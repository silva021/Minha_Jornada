package com.silva021.minhajornada.ui.screens.challenges.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.usecases.GetChallengeByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChallengeSummaryViewModel(
    private val getChallengeById: GetChallengeByIdUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<ChallengeSummaryUiState>(ChallengeSummaryUiState.Loading)
    val uiState = _uiState.asStateFlow()


    fun getChallenge(challengeId: String) {
        viewModelScope.launch {
            getChallengeById(challengeId).onSuccess { challenge ->
                ChallengeSummaryUiState.Success(challenge)
            }.onFailure { error ->
                ChallengeSummaryUiState.Error(error.message ?: "Unknown error")
            }
        }
    }
}

sealed class ChallengeSummaryUiState {
    data class Success(val challenge: com.silva021.minhajornada.domain.model.Challenge) : ChallengeSummaryUiState()
    data class Error(val message: String) : ChallengeSummaryUiState()
    object Loading : ChallengeSummaryUiState()
}