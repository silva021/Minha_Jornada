package com.silva021.minhajornada.ui.screens.communities.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Comment
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.usecases.GetCommentsByPostIdUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunityPostByIdUseCase
import com.silva021.minhajornada.domain.usecases.GetMyProfileUseCase
import com.silva021.minhajornada.ui.screens.communities.feed.UserInfoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommunityPostViewModel(
    private val getPostById: GetCommunityPostByIdUseCase,
    private val getCommentsByPostId: GetCommentsByPostIdUseCase,
    private val getProfile: GetMyProfileUseCase,
) : ViewModel() {
    private val _commentsState =
        MutableStateFlow<CommunityCommentsUiState>(CommunityCommentsUiState.Loading)
    val commentsState = _commentsState.asStateFlow()

    private val _postState = MutableStateFlow<CommunityPostUiState>(CommunityPostUiState.Loading)
    val postState = _postState.asStateFlow()

    private val _userState = MutableStateFlow<UserInfoUiState>(UserInfoUiState.Loading)
    val userState = _userState.asStateFlow()

    fun loadPostInfo(postId: String) {
        loadComments(postId)
        loadPost(postId)
        loadUser()
    }

    private fun loadPost(postId: String) {
        viewModelScope.launch {
            _postState.value = CommunityPostUiState.Loading
            getPostById(postId).onSuccess { post ->
                _postState.value = CommunityPostUiState.Success(post)
            }.onFailure { error ->
                _postState.value = CommunityPostUiState.Error(
                    error.message ?: "Erro ao carregar feed da comunidade"
                )
            }
        }
    }

    private fun loadComments(postId: String) {
        viewModelScope.launch {
            _commentsState.value = CommunityCommentsUiState.Loading
            getCommentsByPostId(postId).onSuccess { comments ->
                _commentsState.value = CommunityCommentsUiState.Success(comments)
            }.onFailure { error ->
                _commentsState.value = CommunityCommentsUiState.Error(
                    error.message ?: "Erro ao carregar comentários"
                )
            }
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            _userState.value = UserInfoUiState.Loading
            getProfile.invoke().onSuccess {
                _userState.value = UserInfoUiState.Success(it)
            }.onFailure {
                _userState.value = UserInfoUiState.Error("Erro ao carregar usuário")
            }
        }
    }
}

sealed class CommunityPostUiState {
    object Loading : CommunityPostUiState()
    data class Success(val post: Post) : CommunityPostUiState()
    data class Error(val message: String) : CommunityPostUiState()
}

sealed class CommunityCommentsUiState {
    object Loading : CommunityCommentsUiState()
    data class Success(val comments: List<Comment>) : CommunityCommentsUiState()
    data class Error(val message: String) : CommunityCommentsUiState()
}
