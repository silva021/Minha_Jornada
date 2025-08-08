package com.silva021.minhajornada.ui.screens.challenges.actives

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Challenge
import com.silva021.minhajornada.domain.usecases.GetChallengesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActiveChallengesViewModel(
    private val getChallenges: GetChallengesUseCase,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<ActiveChallengesUiState>(ActiveChallengesUiState.Loading)
    val uiState: StateFlow<ActiveChallengesUiState> = _uiState.asStateFlow()

    fun getActiveChallenge() {
        viewModelScope.launch {
            _uiState.value = ActiveChallengesUiState.Loading
            getChallenges.invoke().onSuccess {
                _uiState.value = ActiveChallengesUiState.Success(it.actives)
            }.onFailure {
                _uiState.value = ActiveChallengesUiState.Error(
                    message = it.message ?: "An error occurred while fetching challenges"
                )
            }
        }
    }
}

sealed class ActiveChallengesUiState {
    data class Success(val challenges: List<Challenge>) : ActiveChallengesUiState()
    object Loading : ActiveChallengesUiState()
    data class Error(val message: String) : ActiveChallengesUiState()
}