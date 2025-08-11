package com.silva021.minhajornada.ui.screens.communities.post.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.usecases.DeletePostUseCase
import com.silva021.minhajornada.domain.usecases.EditPostUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunityPostByIdUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditPostViewModel(
    private val getPostById: GetCommunityPostByIdUseCase,
    private val editPostUseCase: EditPostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
) : ViewModel() {
    var postId = ""
    var communityId = ""

    private val _uiState = MutableStateFlow<EditPostUiState>(EditPostUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()

    fun loadPost(
        communityId: String,
        postId: String,
    ) {
        viewModelScope.launch {
            _uiState.value = EditPostUiState.Loading
            getPostById(
                communityId = communityId, postId = postId
            ).onSuccess { post ->
                this@EditPostViewModel.postId = postId
                this@EditPostViewModel.communityId = communityId
                _uiState.value = EditPostUiState.Success(post)
            }.onFailure { error ->
                _uiState.value = EditPostUiState.Error(
                    error.message ?: "Erro ao carregar feed da comunidade"
                )
            }
        }
    }

    fun editPost(text: String) {
        viewModelScope.launch {
            _uiState.value = EditPostUiState.Loading
            editPostUseCase(
                communityId = communityId, postId = postId, text = text
            ).onSuccess {
                _eventFlow.emit(NavigationEvent.GoBack)
            }.onFailure { error ->
                _uiState.value = EditPostUiState.Error(
                    error.message ?: "Erro ao editar post"
                )
            }
        }
    }

    fun deletePost() {
        viewModelScope.launch {
            _uiState.value = EditPostUiState.Loading
            deletePostUseCase(communityId = communityId, postId = postId)
                .onSuccess {
                    _eventFlow.emit(NavigationEvent.GoBack)
                }.onFailure { error ->
                    _uiState.value = EditPostUiState.Error(
                        error.message ?: "Erro ao deletar post"
                    )
                }
        }
    }
}

sealed class EditPostUiState {
    object Loading : EditPostUiState()
    data class Success(val post: Post) : EditPostUiState()
    data class Error(val message: String) : EditPostUiState()
}

sealed class NavigationEvent {
    object GoBack : NavigationEvent()
}