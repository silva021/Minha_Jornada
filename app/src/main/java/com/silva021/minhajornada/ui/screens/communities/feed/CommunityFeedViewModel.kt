package com.silva021.minhajornada.ui.screens.communities.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silva021.minhajornada.domain.model.Community
import com.silva021.minhajornada.domain.model.Post
import com.silva021.minhajornada.domain.model.Profile
import com.silva021.minhajornada.domain.usecases.CreatePostUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunityByIdUseCase
import com.silva021.minhajornada.domain.usecases.GetCommunityPostsUseCase
import com.silva021.minhajornada.domain.usecases.GetMyProfileUseCase
import com.silva021.minhajornada.domain.usecases.LeaveCommunityUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommunityFeedViewModel(
    private val getCommunityById: GetCommunityByIdUseCase,
    private val getProfile: GetMyProfileUseCase,
    private val getPosts: GetCommunityPostsUseCase,
    private val leaveCommunityUseCase: LeaveCommunityUseCase,
    private val createPostUseCase: CreatePostUseCase,
) : ViewModel() {
    private var profile: Profile? = null

    private val _communityState =
        MutableStateFlow<CommunityHeaderUiState>(CommunityHeaderUiState.Loading)
    val communityState = _communityState.asStateFlow()

    private val _feedState = MutableStateFlow<CommunityFeedUiState>(CommunityFeedUiState.Loading)
    val feedState = _feedState.asStateFlow()

    private val _userState = MutableStateFlow<UserInfoUiState>(UserInfoUiState.Loading)
    val userState = _userState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val eventFlow: Flow<NavigationEvent> = _eventFlow.asSharedFlow()

    fun loadCommunityScreen(communityId: String) {
        loadCommunityHeader(communityId)
        loadFeed(communityId)
        loadUser()
    }

    private fun loadCommunityHeader(id: String) {
        viewModelScope.launch {
            _communityState.value = CommunityHeaderUiState.Loading
            getCommunityById(id).onSuccess { community ->
                _communityState.value = CommunityHeaderUiState.Success(community)
            }.onFailure { error ->
                _communityState.value = CommunityHeaderUiState.Error(
                    error.message ?: "Erro ao carregar feed da comunidade"
                )
            }
        }
    }

    private fun loadFeed(id: String) {
        viewModelScope.launch {
            _feedState.value = CommunityFeedUiState.Loading
            getPosts(id)
                .onSuccess {
                    _feedState.value = CommunityFeedUiState.Success(it)
                }.onFailure {
                    _feedState.value = CommunityFeedUiState.Error("Erro ao carregar feed")
                }
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            _userState.value = UserInfoUiState.Loading
            getProfile.invoke().onSuccess {
                profile = it
                _userState.value = UserInfoUiState.Success(it)
            }.onFailure {
                _userState.value = UserInfoUiState.Error("Erro ao carregar usuário")
            }
        }
    }

    fun leaveCommunity(communityId: String) {
        viewModelScope.launch {
            _communityState.value = CommunityHeaderUiState.Loading
            _feedState.value = CommunityFeedUiState.Loading
            _userState.value = UserInfoUiState.Loading

            leaveCommunityUseCase(communityId).onSuccess {
                _eventFlow.emit(NavigationEvent.NavigateToCommunities)
            }.onFailure { error ->
                _communityState.value = CommunityHeaderUiState.Error(
                    error.message ?: "Erro ao sair da comunidade"
                )
            }
        }
    }

    fun createPost(
        communityId: String,
        text: String,
    ) {
        viewModelScope.launch {
            profile?.let {
                _communityState.value = CommunityHeaderUiState.Loading
                _feedState.value = CommunityFeedUiState.Loading
                _userState.value = UserInfoUiState.Loading

                createPostUseCase(
                    communityId = communityId,
                    profile = it,
                    text = text,
                ).onSuccess {
                    loadCommunityScreen(communityId)
                }.onFailure {
                    _userState.value = UserInfoUiState.Error("Usuário não encontrado")
                }
            } ?: run {
                _userState.value = UserInfoUiState.Error("Usuário não encontrado")
            }
        }
    }
}

sealed class UserInfoUiState {
    object Loading : UserInfoUiState()
    data class Success(val profile: Profile) : UserInfoUiState()
    data class Error(val message: String) : UserInfoUiState()
}

sealed class CommunityFeedUiState {
    object Loading : CommunityFeedUiState()
    data class Success(val posts: List<Post>) : CommunityFeedUiState()
    data class Error(val message: String) : CommunityFeedUiState()
}

sealed class CommunityHeaderUiState {
    object Loading : CommunityHeaderUiState()
    data class Success(val community: Community) : CommunityHeaderUiState()
    data class Error(val message: String) : CommunityHeaderUiState()
}

sealed class NavigationEvent {
    data object NavigateToCommunities : NavigationEvent()
}