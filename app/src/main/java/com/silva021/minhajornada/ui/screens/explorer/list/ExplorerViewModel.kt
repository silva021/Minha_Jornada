package com.silva021.minhajornada.ui.screens.explorer.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.CategoryType
import com.silva021.minhajornada.domain.model.PublicChallenges
import com.silva021.minhajornada.domain.usecases.GetPublicChallengesByCategoryUseCase
import com.silva021.minhajornada.domain.usecases.GetPublicChallengesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExplorerViewModel(
    private val getPublicChallenges: GetPublicChallengesUseCase,
    private val getPublicChallengesByCategory: GetPublicChallengesByCategoryUseCase,
) : ViewModel() {
    private val _selectedCategory = MutableStateFlow(CategoryType.ALL)
    val selectedCategory: StateFlow<CategoryType> = _selectedCategory

    private val _uiState = MutableStateFlow<ExplorerUiState>(ExplorerUiState.Loading)
    val uiState: StateFlow<ExplorerUiState> = _uiState.asStateFlow()

    fun fetchPublicChallenges() {
        if (_uiState.value.isSuccess().not()) {
            viewModelScope.launch {
                _uiState.value = ExplorerUiState.Loading
                getPublicChallenges().onSuccess {
                    _uiState.value = ExplorerUiState.Success(it)
                }.onFailure {
                    _uiState.value = ExplorerUiState.Error(
                        message = it.message.orEmpty()
                    )
                }
            }
        }
    }

    fun fetchPublicChallengesByCategory(
        categoryType: CategoryType,
    ) {
        viewModelScope.launch {
            _uiState.value = ExplorerUiState.Loading
            getPublicChallengesByCategory(categoryType).onSuccess {
                _uiState.value = ExplorerUiState.Success(it)
            }.onFailure {
                _uiState.value = ExplorerUiState.Error(
                    message = it.message.orEmpty()
                )
            }
        }
    }

    fun onCategorySelected(category: CategoryType) {
        _selectedCategory.value = category

        if (category == CategoryType.ALL) {
            _uiState.value = ExplorerUiState.Loading
            fetchPublicChallenges()
        } else
            fetchPublicChallengesByCategory(category)
    }
}

sealed class ExplorerUiState {
    data class Success(val list: PublicChallenges) : ExplorerUiState()
    object Loading : ExplorerUiState()
    data class Error(val message: String) : ExplorerUiState()

    fun isSuccess() = this is Success
}