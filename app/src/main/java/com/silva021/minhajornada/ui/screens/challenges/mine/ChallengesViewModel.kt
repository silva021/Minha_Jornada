package com.silva021.minhajornada.ui.screens.challenges.mine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Challenges
import com.silva021.minhajornada.domain.usecases.GetChallengesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChallengesViewModel(
    private val getChallenges: GetChallengesUseCase,
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<ChallengesUiState>(ChallengesUiState.Loading)
    val uiState: StateFlow<ChallengesUiState> = _uiState.asStateFlow()


    fun getChallenges() {
        viewModelScope.launch {
            _uiState.value = ChallengesUiState.Loading
            getChallenges.invoke().onSuccess {
                _uiState.value = ChallengesUiState.Success(it)
            }.onFailure {
                _uiState.value = ChallengesUiState.Error(
                    message = it.message ?: "An error occurred while fetching challenges"
                )
            }
        }
    }
}

sealed class ChallengesUiState {
    data class Success(val challenges: Challenges) : ChallengesUiState()
    object Loading : ChallengesUiState()
    data class Error(val message: String) : ChallengesUiState()
}