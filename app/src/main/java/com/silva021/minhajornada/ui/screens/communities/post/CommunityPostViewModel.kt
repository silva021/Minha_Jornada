package com.silva021.minhajornada.ui.screens.communities.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Comment
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.domain.usecases.CreateCommentUseCase
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
    private val createCommentUseCase: CreateCommentUseCase,
) : ViewModel() {
    private val _commentsState =
        MutableStateFlow<CommunityCommentsUiState>(CommunityCommentsUiState.Loading)
    val commentsState = _commentsState.asStateFlow()

    private val _postState = MutableStateFlow<CommunityPostUiState>(CommunityPostUiState.Loading)
    val postState = _postState.asStateFlow()

    private val _userState = MutableStateFlow<UserInfoUiState>(UserInfoUiState.Loading)
    val userState = _userState.asStateFlow()

    var profile: Profile? = null

    fun loadPostInfo(
        communityId: String,
        postId: String,
    ) {
        loadComments(communityId = communityId, postId = postId)
        loadPost(communityId = communityId, postId = postId)
        loadUser()
    }

    private fun loadPost(
        communityId: String,
        postId: String,
    ) {
        viewModelScope.launch {
            _postState.value = CommunityPostUiState.Loading
            getPostById(
                communityId = communityId,
                postId = postId
            ).onSuccess { post ->
                _postState.value = CommunityPostUiState.Success(post)
            }.onFailure { error ->
                _postState.value = CommunityPostUiState.Error(
                    error.message ?: "Erro ao carregar feed da comunidade"
                )
            }
        }
    }

    private fun loadComments(
        communityId: String,
        postId: String,
    ) {
        viewModelScope.launch {
            _commentsState.value = CommunityCommentsUiState.Loading
            getCommentsByPostId(
                communityId = communityId,
                postId = postId
            ).onSuccess { comments ->
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
                this@CommunityPostViewModel.profile = it
                _userState.value = UserInfoUiState.Success(it)
            }.onFailure {
                _userState.value = UserInfoUiState.Error("Erro ao carregar usuário")
            }
        }
    }

    fun createComment(
        postText: String,
        communityId: String,
        postId: String,
    ) {
        viewModelScope.launch {
            profile?.let {
                createCommentUseCase(
                    communityId = communityId,
                    postId = postId,
                    text = postText,
                    owner = it
                ).onSuccess { comments ->
                    loadComments(communityId = communityId, postId = postId)
                }.onFailure { error ->
                    _commentsState.value = CommunityCommentsUiState.Error(
                        error.message ?: "Erro ao criar comentário"
                    )
                }
            } ?: run {
                _commentsState.value = CommunityCommentsUiState.Error("Usuário não encontrado")
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
